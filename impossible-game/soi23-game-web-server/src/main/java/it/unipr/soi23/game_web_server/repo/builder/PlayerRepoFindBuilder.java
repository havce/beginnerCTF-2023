package it.unipr.soi23.game_web_server.repo.builder;

import it.unipr.soi23.game_web_server.model.Player;

import java.util.Optional;

public interface PlayerRepoFindBuilder {

    public Optional<Player> findById(String id);

    public Iterable<Player> findAllByGameId(String gameId);
}
