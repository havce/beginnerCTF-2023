package it.unipr.soi23.game_web_server.repo.builder;

import it.unipr.soi23.game_web_server.model.GameData;

public interface GameDataRepoInsertBuilder {

    public GameDataRepoInsertBuilder gameData(GameData gameData);

    public void apply();
}
