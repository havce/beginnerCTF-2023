package it.unipr.soi23.game_web_server.service;

import it.unipr.soi23.game_web_server.model.*;

public interface Soi23GameWebServerService {

    RegisterResponse register(String gameId, String playerId);

    GameDataDTO startGame(String gameId, StartGameRequest request);

    PlayerDTO movePlayer(String gameId, MovePlayerRequest request);

    BallAnimation animationEnd(String gameId);
}
