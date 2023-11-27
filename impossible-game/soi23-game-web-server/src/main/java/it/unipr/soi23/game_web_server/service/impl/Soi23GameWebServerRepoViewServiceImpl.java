package it.unipr.soi23.game_web_server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import it.unipr.soi23.game_web_server.model.GameData;
import it.unipr.soi23.game_web_server.model.Player;
import it.unipr.soi23.game_web_server.repo.GameDataRepo;
import it.unipr.soi23.game_web_server.repo.PlayerRepo;
import it.unipr.soi23.game_web_server.service.Soi23GameWebServerRepoViewService;

@Service
public class Soi23GameWebServerRepoViewServiceImpl implements Soi23GameWebServerRepoViewService {

    private final GameDataRepo gameDataRepo;
    private final PlayerRepo playerRepo;

    public Soi23GameWebServerRepoViewServiceImpl(GameDataRepo gameDataRepo, PlayerRepo playerRepo) {
        this.gameDataRepo = gameDataRepo;
        this.playerRepo = playerRepo;
    }

    public Map<String, Object> getRepoMap() {
        final Map<String, Object> result = new HashMap<>();
        result.put("games", makeGameMap());
        result.put("players", makePlayers());
        return result;
    }

    // Private

    private Map<String, Object> makeGameMap() {
        final Map<String, Object> gameMap = new HashMap<>();
        final Iterable<GameData> gameDataIt = gameDataRepo.findAll();
        gameDataIt.forEach(gameData -> gameMap.put(gameData.getId(), gameData));
        return gameMap;
    }

    private Map<String, Object> makePlayers() {
        final Map<String, Object> playerMap = new HashMap<>();
        final Iterable<Player> playerIt = playerRepo.findAll();
        playerIt.forEach(player -> playerMap.put(player.getId(), player));
        return playerMap;
    }
}
