package it.unipr.soi23.game_web_server.model;

public class RegisterResponse extends WatchGameResponse {

    private String token;

    @Override
    public RegisterResponse teamsScore(TeamsScore teamsScore) {
        super.teamsScore(teamsScore);
        return this;
    }

    @Override
    public RegisterResponse ballAnimation(BallAnimation ballAnimation) {
        super.ballAnimation(ballAnimation);
        return this;
    }

    @Override
    public RegisterResponse message(Message message) {
        super.message(message);
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterResponse token(String token) {
        setToken(token);
        return this;
    }
}
