package it.unipr.soi23.game_web_server.repo.impl;

import it.unipr.soi23.game_web_server.repo.GameDataRepo;
import it.unipr.soi23.game_web_server.repo.PersistenceRepo;
import it.unipr.soi23.game_web_server.repo.PlayerRepo;
import it.unipr.soi23.game_web_server.repo.builder.GameDataRepoFindBuilder;
import it.unipr.soi23.game_web_server.repo.builder.GameDataRepoInsertBuilder;
import it.unipr.soi23.game_web_server.repo.builder.PlayerRepoFindBuilder;
import it.unipr.soi23.game_web_server.repo.builder.PlayerRepoInsertBuilder;
import it.unipr.soi23.game_web_server.repo.builder.impl.GameDataRepoFindBuilderImpl;
import it.unipr.soi23.game_web_server.repo.builder.impl.GameDataRepoInsertBuilderImpl;
import it.unipr.soi23.game_web_server.repo.builder.impl.PlayerRepoFindBuilderImpl;
import it.unipr.soi23.game_web_server.repo.builder.impl.PlayerRepoInsertBuilderImpl;

public abstract class AbstractPersistenceRepoRedisImpl implements PersistenceRepo {

    private final GameDataRepo gameDataRepo;
    private final PlayerRepo playerRepo;

    protected AbstractPersistenceRepoRedisImpl(GameDataRepo gameDataRepo, PlayerRepo playerRepo) {
        this.gameDataRepo = gameDataRepo;
        this.playerRepo = playerRepo;
    }

    @Override
    public GameDataRepoFindBuilder findGameData() {
        return new GameDataRepoFindBuilderImpl(gameDataRepo);
    }

    @Override
    public GameDataRepoInsertBuilder insertGameData() {
        return new GameDataRepoInsertBuilderImpl(gameDataRepo);
    }

    @Override
    public PlayerRepoFindBuilder findPlayer() {
        return new PlayerRepoFindBuilderImpl(playerRepo);
    }

    @Override
    public PlayerRepoInsertBuilder insertPlayer() {
        return new PlayerRepoInsertBuilderImpl(playerRepo);
    }
}
