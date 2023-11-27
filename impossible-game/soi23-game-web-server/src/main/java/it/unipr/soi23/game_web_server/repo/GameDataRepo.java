package it.unipr.soi23.game_web_server.repo;

import it.unipr.soi23.game_web_server.model.GameData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDataRepo extends CrudRepository<GameData, String> {
}
