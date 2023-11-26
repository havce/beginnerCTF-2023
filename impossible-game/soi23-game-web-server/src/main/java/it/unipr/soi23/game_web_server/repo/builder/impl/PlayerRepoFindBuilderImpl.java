package it.unipr.soi23.game_web_server.repo.builder.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;

import it.unipr.soi23.game_web_server.model.Player;
import it.unipr.soi23.game_web_server.repo.PlayerRepo;
import it.unipr.soi23.game_web_server.repo.builder.PlayerRepoFindBuilder;

public class PlayerRepoFindBuilderImpl implements PlayerRepoFindBuilder {

    private final PlayerRepo playerRepo;

    public PlayerRepoFindBuilderImpl(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public Optional<Player> findById(String id) {
        return playerRepo.findById(id);
    }

    @Override
    public Iterable<Player> findAllByGameId(String gameId) {
        return playerRepo.findAll(Example.of(new Player().gameId(gameId)));
    }
}
