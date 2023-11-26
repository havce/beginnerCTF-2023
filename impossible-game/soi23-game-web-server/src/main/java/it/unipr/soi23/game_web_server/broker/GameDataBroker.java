package it.unipr.soi23.game_web_server.broker;

import it.unipr.soi23.game_web_server.model.BallAnimation;
import it.unipr.soi23.game_web_server.model.BallCollisionResult;
import it.unipr.soi23.game_web_server.model.GameData;
import it.unipr.soi23.game_web_server.model.Player;

import static it.unipr.soi23.game_web_server.utils.Soi23GameWebServerConst.*;

public class GameDataBroker {

    private GameData gameData;
    private Iterable<Player> players;

    public enum UpdateAnimationResult {
        /**
         * The request has been declined
         */
        NONE,
        /**
         * There is a new animation available
         */
        NEXT,
        /**
         * One team scored
         */
        SCORE
    }

    public GameDataBroker gameData(GameData gameData) {
        this.gameData = gameData;
        return this;
    }

    public GameDataBroker players(Iterable<Player> players) {
        this.players = players;
        return this;
    }

    public void startGame() {
        if (!gameData.isPlaying()) {
            gameData.setBallAnimation(new BallAnimationBroker() //
                    .x(BALL_INITIAL_X) //
                    .y(BALL_INITIAL_Y) //
                    .direction(BALL_INITIAL_DIRECTION) //
                    .build());
            gameData.setPlaying(true);
        }
    }

    /**
     * Request to update the {@link BallAnimation}
     *
     * @return UpdateAnimationResult
     */
    public UpdateAnimationResult updateAnimation() {
        if (gameData.isPlaying()) {
            final BallAnimation ballAnimation = gameData.getBallAnimation();
            if (ballAnimation.getEndTimestamp() <= System.currentTimeMillis()) {
                final BallCollisionResult ballCollisionResult = new BallAnimationBroker() //
                        .ballAnimation(ballAnimation) //
                        .collision(players);
                if (ballCollisionResult == null) {
                    return UpdateAnimationResult.NEXT;
                }
                if (ballCollisionResult == BallCollisionResult.RIGHT_TEAM_POINT) {
                    gameData.getTeamsScore().increaseRightTeamScore();
                } else if (ballCollisionResult == BallCollisionResult.LEFT_TEAM_POINT) {
                    gameData.getTeamsScore().increaseLeftTeamScore();
                }
                gameData.setPlaying(false);
                return UpdateAnimationResult.SCORE;
            }
        }
        return UpdateAnimationResult.NONE;
    }
}
