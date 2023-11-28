package it.unipr.soi23.game_web_server.model;

public class GameDataDTO {

    private TeamsScore teamsScore;
    private BallAnimation ballAnimation;

    public TeamsScore getTeamsScore() {
        return teamsScore;
    }

    public void setTeamsScore(TeamsScore teamsScore) {
        this.teamsScore = teamsScore;
    }

    public GameDataDTO teamsScore(TeamsScore teamsScore) {
        setTeamsScore(teamsScore);
        return this;
    }

    public BallAnimation getBallAnimation() {
        return ballAnimation;
    }

    public void setBallAnimation(BallAnimation ballAnimation) {
        this.ballAnimation = ballAnimation;
    }

    public GameDataDTO ballAnimation(BallAnimation ballAnimation) {
        setBallAnimation(ballAnimation);
        return this;
    }
}
