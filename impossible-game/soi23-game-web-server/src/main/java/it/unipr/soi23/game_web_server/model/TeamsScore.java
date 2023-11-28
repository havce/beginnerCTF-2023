package it.unipr.soi23.game_web_server.model;

import java.io.Serial;
import java.io.Serializable;

public class TeamsScore implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int leftTeamScore = 0;
    private int rightTeamScore = 0;

    public int getLeftTeamScore() {
        return leftTeamScore;
    }

    public void setLeftTeamScore(int leftTeamScore) {
        this.leftTeamScore = leftTeamScore;
    }

    public void increaseLeftTeamScore() {
        setLeftTeamScore(this.leftTeamScore + 1);
    }

    public int getRightTeamScore() {
        return rightTeamScore;
    }

    public void setRightTeamScore(int rightTeamScore) {
        this.rightTeamScore = rightTeamScore;
    }

    public void increaseRightTeamScore() {
        setRightTeamScore(this.rightTeamScore + 1);
    }
}
