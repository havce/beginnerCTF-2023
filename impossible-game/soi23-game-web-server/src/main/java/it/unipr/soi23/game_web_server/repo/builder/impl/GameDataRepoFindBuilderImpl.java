package it.unipr.soi23.game_web_server.repo.builder.impl;

import java.util.Optional;

import it.unipr.soi23.game_web_server.model.GameData;
import it.unipr.soi23.game_web_server.repo.GameDataRepo;
import it.unipr.soi23.game_web_server.repo.builder.GameDataRepoFindBuilder;

public class GameDataRepoFindBuilderImpl implements GameDataRepoFindBuilder {

    private final GameDataRepo gameDataRepo;

    public GameDataRepoFindBuilderImpl(GameDataRepo gameDataRepo) {
        this.gameDataRepo = gameDataRepo;
    }

    @Override
    public Optional<GameData> findById(String id) {
        return gameDataRepo.findById(id);
    }
}
