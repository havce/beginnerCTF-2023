package it.unipr.soi23.game_web_server.repo;

import it.unipr.soi23.game_web_server.repo.builder.*;

public interface PersistenceRepo {

    public GameDataRepoFindBuilder findGameData();

    public GameDataRepoInsertBuilder insertGameData();

    public GameDataRepoUpdateBuilder updateGameData(String id);

    public PlayerRepoFindBuilder findPlayer();

    public PlayerRepoInsertBuilder insertPlayer();

    public PlayerRepoUpdateBuilder updatePlayer(String id);
}
