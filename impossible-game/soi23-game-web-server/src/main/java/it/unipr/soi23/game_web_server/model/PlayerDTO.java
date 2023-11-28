package it.unipr.soi23.game_web_server.model;

import static it.unipr.soi23.game_web_server.utils.Soi23GameWebServerConst.PLAYER_ID_SEPARATOR;

public class PlayerDTO {

    private String id;
    private boolean readyToStart;
    private Player.Team team;
    private int y;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlayerDTO id(String id) {
        setId(id);
        return this;
    }

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public void setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
    }

    public PlayerDTO readyToStart(boolean readyToStart) {
        setReadyToStart(readyToStart);
        return this;
    }

    public Player.Team getTeam() {
        return team;
    }

    public void setTeam(Player.Team team) {
        this.team = team;
    }

    public PlayerDTO team(Player.Team team) {
        setTeam(team);
        return this;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PlayerDTO y(int y) {
        setY(y);
        return this;
    }

    public PlayerDTO fromPlayer(Player player) {
        final int idSuffixLen = player.getGameId().length() + PLAYER_ID_SEPARATOR.length();
        final String fullPlayerId = player.getId();
        final String playerId = fullPlayerId.substring(0, fullPlayerId.length() - idSuffixLen);
        setId(playerId);
        setReadyToStart(player.isReadyToStart());
        setTeam(player.getTeam());
        setY(player.getY());
        return this;
    }
}
