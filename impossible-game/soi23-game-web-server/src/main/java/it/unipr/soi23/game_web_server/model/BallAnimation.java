package it.unipr.soi23.game_web_server.model;

import java.io.Serial;
import java.io.Serializable;

public class BallAnimation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    /**
     * Animation duration in seconds
     */
    private double time;
    /**
     * Timestamp in which the animation should end
     */
    private long endTimestamp;

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
