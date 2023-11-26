package it.unipr.soi23.game_web_server.repo.builder.impl;

import it.unipr.soi23.game_web_server.model.Player;
import it.unipr.soi23.game_web_server.repo.PlayerRepo;
import it.unipr.soi23.game_web_server.repo.builder.PlayerRepoInsertBuilder;

public class PlayerRepoInsertBuilderImpl implements PlayerRepoInsertBuilder {

    private final PlayerRepo playerRepo;

    private Player player;

    public PlayerRepoInsertBuilderImpl(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public PlayerRepoInsertBuilder player(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public void apply() {
        playerRepo.save(player);
    }
}
