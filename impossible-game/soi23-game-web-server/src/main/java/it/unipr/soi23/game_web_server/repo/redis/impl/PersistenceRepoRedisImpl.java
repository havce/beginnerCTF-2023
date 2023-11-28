package it.unipr.soi23.game_web_server.repo.redis.impl;

import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.stereotype.Repository;

import it.unipr.soi23.game_web_server.repo.GameDataRepo;
import it.unipr.soi23.game_web_server.repo.PlayerRepo;
import it.unipr.soi23.game_web_server.repo.builder.GameDataRepoUpdateBuilder;
import it.unipr.soi23.game_web_server.repo.builder.PlayerRepoUpdateBuilder;
import it.unipr.soi23.game_web_server.repo.builder.redis.impl.GameDataRepoUpdateBuilderRedisImpl;
import it.unipr.soi23.game_web_server.repo.builder.redis.impl.PlayerRepoUpdateBuilderRedisImpl;
import it.unipr.soi23.game_web_server.repo.impl.AbstractPersistenceRepoRedisImpl;

@Repository
public class PersistenceRepoRedisImpl extends AbstractPersistenceRepoRedisImpl {

    private final RedisKeyValueTemplate redisKVTemplate;

    public PersistenceRepoRedisImpl(RedisKeyValueTemplate redisKVTemplate, GameDataRepo gameDataRepo,
            PlayerRepo playerRepo) {
        super(gameDataRepo, playerRepo);
        this.redisKVTemplate = redisKVTemplate;
    }

    @Override
    public GameDataRepoUpdateBuilder updateGameData(String id) {
        return new GameDataRepoUpdateBuilderRedisImpl(redisKVTemplate, id);
    }

    @Override
    public PlayerRepoUpdateBuilder updatePlayer(String id) {
        return new PlayerRepoUpdateBuilderRedisImpl(redisKVTemplate, id);
    }
}
