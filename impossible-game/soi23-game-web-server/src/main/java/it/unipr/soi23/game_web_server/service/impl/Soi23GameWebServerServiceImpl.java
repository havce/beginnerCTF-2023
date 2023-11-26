package it.unipr.soi23.game_web_server.service.impl;

import it.unipr.soi23.game_web_server.broker.GameDataBroker;
import it.unipr.soi23.game_web_server.broker.PlayerBroker;
import it.unipr.soi23.game_web_server.model.*;
import it.unipr.soi23.game_web_server.repo.PersistenceRepo;
import it.unipr.soi23.game_web_server.service.Soi23GameWebServerService;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.Random;
//import java.util.HashMap;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static it.unipr.soi23.game_web_server.utils.Soi23GameWebServerConst.*;

@Service
public class Soi23GameWebServerServiceImpl implements Soi23GameWebServerService {

    private final MessageSendingOperations<String> messageSendingOperations;
    private final PersistenceRepo persistenceRepo;

    public Soi23GameWebServerServiceImpl( //
                                          MessageSendingOperations<String> messageSendingOperations, //
                                          PersistenceRepo persistenceRepo //
    ) {
        this.messageSendingOperations = messageSendingOperations;
        this.persistenceRepo = persistenceRepo;
    }

    @Override
    public RegisterResponse register(String gameId, String playerId) {
        final GameData gameDataRead = persistenceRepo.findGameData().findById(gameId).orElse(null);
        final boolean gameDataFound = gameDataRead != null;
        final GameData gameData = gameDataFound ? gameDataRead : new GameData(gameId);
        
        // create AI player before human player registration
        final Player opponentPlayer = createPlayer(gameId, AI_PLAYER_NAME);
        // the opponent player is ready as soon as the "human" player connects
        opponentPlayer.setReadyToStart(true);
        // register player to persistenceRepo
        persistenceRepo.insertPlayer().player(opponentPlayer).apply();

        final Player player = createPlayer(gameId, playerId);
        if (player == null) {
            return new RegisterResponse().message(new Message() //
                    .type(Message.Type.ERROR) //
                    .code(Message.Code.PLAYER_ID_ALREADY_USED));
        }

        if (gameData.isPlaying()) {
            player.setReadyToStart(true);
        }
        persistenceRepo.insertPlayer().player(player).apply();
        if (!gameDataFound) {
            persistenceRepo.insertGameData().gameData(gameData).apply();
        }

        messageSendingOperations.convertAndSend( //
                TOPIC_GAME_PREFIX + gameId + TOPIC_GAME_PLAYERS_SUFFIX, //
                new PlayerDTO().fromPlayer(player));

        final Iterable<Player> players = persistenceRepo.findPlayer().findAllByGameId(gameId);

        final RegisterResponse response = new RegisterResponse() //
                .teamsScore(gameData.getTeamsScore()) //
                .ballAnimation(gameData.getBallAnimation()) //
                .token(player.getToken());
        players.forEach(response::addPlayer);

        return response;
    }

    @Override
    public GameDataDTO startGame(String gameId, StartGameRequest request) {
        final String fullPlayerId = retrieveFullPlayerId(gameId, request.getPlayerId());
        /*
        Update the readyToStart property of the player and send the new Player
        to the front-end.
        Then, start the game if it can, or reset its ballAnimation to BALL_LOBBY_ANIMATION.
        Finally return the new GameData.
        NOTE: Keep in mind to persist the updates in the repository
         */
        
        final GameData gameData = retrieveGameData(gameId, request.getToken());
        final Player player = retrievePlayer(gameId, fullPlayerId, request.getToken());
		
        player.setReadyToStart(true);
		// persist player changes (update only readyToStart variable, perform a partial update for performance reasons)
		persistenceRepo.updatePlayer(player.getId()).readyToStart(player.isReadyToStart()).apply();

		messageSendingOperations.convertAndSend( //
				TOPIC_GAME_PREFIX + gameId + TOPIC_GAME_PLAYERS_SUFFIX, //
				new PlayerDTO().fromPlayer(player));

		if(canGameStart(gameId)) {
			new GameDataBroker().gameData(gameData).startGame();
		} else {
			gameData.setBallAnimation(BALL_LOBBY_ANIMATION);
		}
		
		// persist gameData changes (update everything)
        persistenceRepo.updateGameData(gameId).gameData(gameData).apply();
        
        return new GameDataDTO()
				.teamsScore(gameData.getTeamsScore())
				.ballAnimation(gameData.getBallAnimation());
    }

    @Override
    public PlayerDTO movePlayer(String gameId, MovePlayerRequest request) {
        final String fullPlayerId = retrieveFullPlayerId(gameId, request.getPlayerId());
        /*
        Update the Y value of the player using PlayerBroker and return it.
        NOTE: Keep in mind to persist the updates in the repository
         */
        
        final Player player = retrievePlayer(gameId, fullPlayerId, request.getToken());

		// set Y value to the player
		new PlayerBroker().player(player).moveToY(request.getY());
		// persist player changes (update only y variable, perform a partial update for performance reasons)
		persistenceRepo.updatePlayer(player.getId()).y(player.getY()).apply();

		return new PlayerDTO().fromPlayer(player);
    }

    @Override
    public BallAnimation animationEnd(String gameId) {
        final GameData gameData = retrieveGameData(gameId, null);
        final Iterable<Player> players = persistenceRepo.findPlayer().findAllByGameId(gameId);
        final GameDataBroker.UpdateAnimationResult updateAnimationResult = new GameDataBroker() //
                .players(players) //
                .gameData(gameData) //
                .updateAnimation();

        // persist gameData changes (update everything)
        persistenceRepo.updateGameData(gameId).gameData(gameData).apply();
        
        Player humanPlayer = null;
        Player AIPlayer = null;
        for(Player player: players) {
        	if(player.getId().equals(retrieveFullPlayerId(gameId, AI_PLAYER_NAME))) AIPlayer = player;
        	else 																	humanPlayer = player;
        }
        
        BallAnimation ballAnimationReturn;
		switch(updateAnimationResult) {
		case SCORE:
			if(humanPlayer != null) {
				humanPlayer.setReadyToStart(false);
				// persist player changes (update only readyToStart variable, perform a partial update for performance reasons)
				persistenceRepo.updatePlayer(humanPlayer.getId()).readyToStart(humanPlayer.isReadyToStart()).apply();
				sendMessage(gameId, humanPlayer.getToken(), Message.Type.INFO, Message.Code.POINT_SCORED, "");
				
				if(gameData.getTeamsScore().getLeftTeamScore() > 0) {
					sendMessage(gameId, humanPlayer.getToken(), Message.Type.INFO, Message.Code.FLAG, System.getenv("flag"));
				}
			}
			
			gameData.setBallAnimation(BALL_LOBBY_ANIMATION);
			// persist gameData changes (update everything)
			persistenceRepo.updateGameData(gameId).gameData(gameData).apply();

			// intentional fall-through
		case NEXT:
			ballAnimationReturn = gameData.getBallAnimation();
			
			// update opponent position
	        if(AIPlayer != null) updateOpponentPosition(gameId, AIPlayer.getId(), humanPlayer.getId(), ballAnimationReturn);
	        
	        // send update to frontend
	        for(Player player: players) {
				messageSendingOperations.convertAndSend( //
						TOPIC_GAME_PREFIX + gameId + TOPIC_GAME_PLAYERS_SUFFIX, //
						new PlayerDTO().fromPlayer(player));
			}
	        
			break;

		case NONE:
			// intentional fall-through
		default:
			ballAnimationReturn = null;
			break;
		}
		
		return ballAnimationReturn;
    }

    // Private

    private String retrieveFullPlayerId(String gameId, String playerId) {
        return playerId.concat(PLAYER_ID_SEPARATOR).concat(gameId);
    }

    private GameData retrieveGameData(String gameId, String token) {
        final GameData gameData = persistenceRepo.findGameData().findById(gameId).orElse(null);
        if (gameData == null) {
            /*
            Send a message to the player to notify the error
             */
        	sendMessage(gameId, token, Message.Type.ERROR, Message.Code.GAME_NOT_FOUND, "");
            throw new GameWebServerException(GAME_NOT_FOUND + gameId);
        }
        return gameData;
    }

    private Player retrievePlayer(String gameId, String playerId, String token) {
        final Player player = persistenceRepo.findPlayer().findById(playerId).orElse(null);
        if (player == null) {
            /*
            Send a message to the player to notify the error
             */
        	sendMessage(gameId, token, Message.Type.ERROR, Message.Code.PLAYER_NOT_FOUND, "");
            throw new GameWebServerException(PLAYER_NOT_FOUND + playerId);
        }
        final boolean isValidToken = new PlayerBroker() //
                .player(player) //
                .checkPlayerToken(token);
        if (!isValidToken) {
            /*
            Send a message to the player to notify the error
             */
        	sendMessage(gameId, token, Message.Type.ERROR, Message.Code.INVALID_PLAYER_TOKEN, "");
            throw new GameWebServerException(INVALID_PLAYER_TOKEN + playerId);
        }
        return player;
    }
    
    private void updateOpponentPosition(String gameId, String AIPlayerId, String humanPlayerId, BallAnimation ballAnimation) {
    	final Player AIPlayer = persistenceRepo.findPlayer().findById(AIPlayerId).get();
    	
    	int y = 0;
    	// super secret name, use only if you know what you are doing!!
    	if(humanPlayerId.equals(retrieveFullPlayerId(gameId, "t3st_ftw_98572"))) y = PLAYER_MIN_Y + new Random().nextInt(PLAYER_MAX_Y - PLAYER_MIN_Y);
    	else 																	 y = ballAnimation.getEndY();
    	
    	new PlayerBroker().player(AIPlayer).moveToY(y);
		persistenceRepo.updatePlayer(AIPlayer.getId()).y(AIPlayer.getY()).apply();
    }

    private Player createPlayer(String gameId, String playerId) {
        final String fullPlayerId = retrieveFullPlayerId(gameId, playerId);
        final boolean playerAlreadyExists = persistenceRepo.findPlayer().findById(fullPlayerId).isPresent();
        if (playerAlreadyExists) {
            return null;
        }

        final String token = UUID.randomUUID().toString();
        /*
        Instantiate and return the new Player.
        NOTE: Use fullPlayerId as id
         */
        
        final Iterable<Player> players = persistenceRepo.findPlayer().findAllByGameId(gameId);
        
        final long leftTeamCount  = StreamSupport.stream(players.spliterator(), false).filter(player -> player.getTeam().equals(Player.Team.LEFT)).count();
        final long rightTeamCount = StreamSupport.stream(players.spliterator(), false).filter(player -> player.getTeam().equals(Player.Team.RIGHT)).count();
    	Player.Team selectedTeam = leftTeamCount < rightTeamCount ? Player.Team.LEFT : Player.Team.RIGHT;
    	
		return new Player()
				.gameId(gameId)
				.id(fullPlayerId)
				.y(PLAYER_INITIAL_Y)
				.team(selectedTeam)
				.token(token)
				.lastMovementTimestamp(System.currentTimeMillis());
    }

    private boolean canGameStart(String gameId) {
        /*
        The game can start if:
            - All players are ready to start
            - There is at least one player for each team (side)
         */
    	
    	final Iterable<Player> players = persistenceRepo.findPlayer().findAllByGameId(gameId);
    	final boolean allReady        = StreamSupport.stream(players.spliterator(), false).allMatch(player -> player.isReadyToStart());
		final boolean atLeastOneLeft  = StreamSupport.stream(players.spliterator(), false).anyMatch(player -> player.getTeam().equals(Player.Team.LEFT));
		final boolean atLeastOneRight = StreamSupport.stream(players.spliterator(), false).anyMatch(player -> player.getTeam().equals(Player.Team.RIGHT));
		return allReady && atLeastOneLeft && atLeastOneRight;
    }

    private void sendMessage(String gameId, String token, Message.Type type, Message.Code code, String customMessage) {
        final Message msg = new Message() //
                .type(type) //
                .code(code) //
                .customMessage(customMessage);
        messageSendingOperations.convertAndSend( //
                TOPIC_GAME_PREFIX + gameId + TOPIC_GAME_MESSAGES_SUFFIX + token, //
                msg);
    }
}
