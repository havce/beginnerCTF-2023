package it.unipr.soi23.game_web_server.repo.builder;

import it.unipr.soi23.game_web_server.model.Player;

public interface PlayerRepoInsertBuilder {

    public PlayerRepoInsertBuilder player(Player player);

    public void apply();
}
