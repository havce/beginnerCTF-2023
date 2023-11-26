package it.unipr.soi23.game_web_server.repo.builder.redis.impl;

import it.unipr.soi23.game_web_server.model.Player;
import it.unipr.soi23.game_web_server.repo.builder.PlayerRepoUpdateBuilder;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;

public class PlayerRepoUpdateBuilderRedisImpl implements PlayerRepoUpdateBuilder {
    private static final String TEAM = "team";
    private static final String Y = "y";
    private static final String READY_TO_START = "readyToStart";
    private static final String LAST_MOVEMENT_TIMESTAMP = "lastMovementTimestamp";

    private final RedisKeyValueTemplate redisKVTemplate;

    private PartialUpdate<Player> partialUpdate;

    public PlayerRepoUpdateBuilderRedisImpl(RedisKeyValueTemplate redisKVTemplate, String id) {
        this.redisKVTemplate = redisKVTemplate;
        this.partialUpdate = new PartialUpdate<>(id, Player.class);
    }

    @Override
    public PlayerRepoUpdateBuilder player(Player player) {
        team(player.getTeam());
        y(player.getY());
        readyToStart(player.isReadyToStart());
        lastMovementTimestamp(player.getLastMovementTimestamp());
        return this;
    }

    @Override
    public PlayerRepoUpdateBuilder team(Player.Team team) {
        partialUpdate = partialUpdate.set(TEAM, team);
        return this;
    }

    @Override
    public PlayerRepoUpdateBuilder y(int y) {
        partialUpdate = partialUpdate.set(Y, y);
        return this;
    }

    @Override
    public PlayerRepoUpdateBuilderRedisImpl readyToStart(boolean readyToStart) {
        partialUpdate = partialUpdate.set(READY_TO_START, readyToStart);
        return this;
    }

    @Override
    public PlayerRepoUpdateBuilderRedisImpl lastMovementTimestamp(long lastMovementTimestamp) {
        partialUpdate = partialUpdate.set(LAST_MOVEMENT_TIMESTAMP, lastMovementTimestamp);
        return this;
    }

    @Override
    public void apply() {
        redisKVTemplate.update(partialUpdate);
    }
}
