package it.unipr.soi23.game_web_server.repo;

import it.unipr.soi23.game_web_server.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends CrudRepository<Player, String>, QueryByExampleExecutor<Player> {
}
