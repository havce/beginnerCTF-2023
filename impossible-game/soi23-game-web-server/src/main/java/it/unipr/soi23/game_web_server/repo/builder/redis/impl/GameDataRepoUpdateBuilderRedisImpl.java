package it.unipr.soi23.game_web_server.repo.builder.redis.impl;

import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;

import it.unipr.soi23.game_web_server.model.BallAnimation;
import it.unipr.soi23.game_web_server.model.GameData;
import it.unipr.soi23.game_web_server.model.TeamsScore;
import it.unipr.soi23.game_web_server.repo.builder.GameDataRepoUpdateBuilder;

public class GameDataRepoUpdateBuilderRedisImpl implements GameDataRepoUpdateBuilder {

    private static final String IS_PLAYING = "isPlaying";
    private static final String TEAMS_SCORE = "teamsScore";
    private static final String BALL_ANIMATION = "ballAnimation";

    private final RedisKeyValueTemplate redisKVTemplate;

    private PartialUpdate<GameData> partialUpdate;

    public GameDataRepoUpdateBuilderRedisImpl(RedisKeyValueTemplate redisKVTemplate, String id) {
        this.redisKVTemplate = redisKVTemplate;
        this.partialUpdate = new PartialUpdate<>(id, GameData.class);
    }

    @Override
    public GameDataRepoUpdateBuilder gameData(GameData gameData) {
        isPlaying(gameData.isPlaying());
        teamsScore(gameData.getTeamsScore());
        ballAnimation(gameData.getBallAnimation());
        return this;
    }

    @Override
    public GameDataRepoUpdateBuilderRedisImpl isPlaying(boolean isPlaying) {
        partialUpdate = partialUpdate.set(IS_PLAYING, isPlaying);
        return this;
    }

    @Override
    public GameDataRepoUpdateBuilderRedisImpl teamsScore(TeamsScore teamsScore) {
        partialUpdate = partialUpdate.set(TEAMS_SCORE, teamsScore);
        return this;
    }

    @Override
    public GameDataRepoUpdateBuilderRedisImpl ballAnimation(BallAnimation ballAnimation) {
        partialUpdate = partialUpdate.set(BALL_ANIMATION, ballAnimation);
        return this;
    }

    @Override
    public void apply() {
        redisKVTemplate.update(partialUpdate);
    }
}
