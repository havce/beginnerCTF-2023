package it.unipr.soi23.game_web_server.repo.builder.impl;

import it.unipr.soi23.game_web_server.model.GameData;
import it.unipr.soi23.game_web_server.repo.GameDataRepo;
import it.unipr.soi23.game_web_server.repo.builder.GameDataRepoInsertBuilder;

public class GameDataRepoInsertBuilderImpl implements GameDataRepoInsertBuilder {

    private final GameDataRepo gameDataRepo;

    private GameData gameData;

    public GameDataRepoInsertBuilderImpl(GameDataRepo gameDataRepo) {
        this.gameDataRepo = gameDataRepo;
    }

    @Override
    public GameDataRepoInsertBuilder gameData(GameData gameData) {
        this.gameData = gameData;
        return this;
    }

    @Override
    public void apply() {
        gameDataRepo.save(gameData);
    }
}
