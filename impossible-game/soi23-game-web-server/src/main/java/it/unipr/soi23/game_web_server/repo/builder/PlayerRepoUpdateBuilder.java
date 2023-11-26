package it.unipr.soi23.game_web_server.repo.builder;

import it.unipr.soi23.game_web_server.model.Player;

public interface PlayerRepoUpdateBuilder {

    public PlayerRepoUpdateBuilder player(Player player);

    public PlayerRepoUpdateBuilder team(Player.Team team);

    public PlayerRepoUpdateBuilder y(int y);

    public PlayerRepoUpdateBuilder readyToStart(boolean readyToStart);

    public PlayerRepoUpdateBuilder lastMovementTimestamp(long lastMovementTimestamp);

    public void apply();
}
