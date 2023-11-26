package it.unipr.soi23.game_web_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

import static it.unipr.soi23.game_web_server.utils.Soi23GameWebServerConst.BALL_LOBBY_ANIMATION;

@RedisHash("GameData")
public class GameData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private final String id;

    private boolean isPlaying;
    private TeamsScore teamsScore;
    private BallAnimation ballAnimation;

    public GameData(String id) {
        this.id = id;
        this.isPlaying = false;
        this.teamsScore = new TeamsScore();
        this.ballAnimation = BALL_LOBBY_ANIMATION;
    }

    public String getId() {
        return id;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public TeamsScore getTeamsScore() {
        return teamsScore;
    }

    public void setTeamsScore(TeamsScore teamsScore) {
        this.teamsScore = teamsScore;
    }

    public BallAnimation getBallAnimation() {
        return ballAnimation;
    }

    public void setBallAnimation(BallAnimation ballAnimation) {
        this.ballAnimation = ballAnimation;
    }
}
