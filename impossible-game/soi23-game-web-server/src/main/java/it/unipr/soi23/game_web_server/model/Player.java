package it.unipr.soi23.game_web_server.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serial;
import java.io.Serializable;

@RedisHash("Player")
public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public enum Team {
        LEFT, //
        RIGHT;

        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }

    // ID has the following structure: gameId.concat("@").concat(playerName)
    @Id
    private String id;
    @Indexed
    private String gameId;
    private String token;
    private Team team;
    private int y;
    private boolean readyToStart;
    private long lastMovementTimestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player id(String id) {
        setId(id);
        return this;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Player gameId(String gameId) {
        setGameId(gameId);
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Player token(String token) {
        setToken(token);
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player team(Team team) {
        setTeam(team);
        return this;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Player y(int y) {
        setY(y);
        return this;
    }

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public void setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
    }

    public Player readyToStart(boolean readyToStart) {
        setReadyToStart(readyToStart);
        return this;
    }

    public long getLastMovementTimestamp() {
        return lastMovementTimestamp;
    }

    public void setLastMovementTimestamp(long lastMovementTimestamp) {
        this.lastMovementTimestamp = lastMovementTimestamp;
    }

    public Player lastMovementTimestamp(long lastMovementTimestamp) {
        setLastMovementTimestamp(lastMovementTimestamp);
        return this;
    }
}
