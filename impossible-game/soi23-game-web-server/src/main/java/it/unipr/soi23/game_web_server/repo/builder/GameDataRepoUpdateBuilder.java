package it.unipr.soi23.game_web_server.repo.builder;

import it.unipr.soi23.game_web_server.model.BallAnimation;
import it.unipr.soi23.game_web_server.model.GameData;
import it.unipr.soi23.game_web_server.model.TeamsScore;

public interface GameDataRepoUpdateBuilder {

    public GameDataRepoUpdateBuilder gameData(GameData gameData);

    public GameDataRepoUpdateBuilder isPlaying(boolean isPlaying);

    public GameDataRepoUpdateBuilder teamsScore(TeamsScore teamsScore);

    public GameDataRepoUpdateBuilder ballAnimation(BallAnimation ballAnimation);

    public void apply();
}
