package it.unipr.soi23.game_web_server.model;

public class StartGameRequest {

    private String playerId;
    private String token;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
