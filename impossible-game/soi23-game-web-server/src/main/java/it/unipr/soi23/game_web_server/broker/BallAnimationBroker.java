package it.unipr.soi23.game_web_server.broker;

import it.unipr.soi23.game_web_server.model.BallAnimation;
import it.unipr.soi23.game_web_server.model.BallCollisionResult;
import it.unipr.soi23.game_web_server.model.Player;

import java.util.stream.StreamSupport;

import static it.unipr.soi23.game_web_server.model.BallCollisionResult.LEFT_TEAM_POINT;
import static it.unipr.soi23.game_web_server.model.BallCollisionResult.RIGHT_TEAM_POINT;
import static it.unipr.soi23.game_web_server.utils.Soi23GameWebServerConst.*;

public class BallAnimationBroker {

    private static final double SIN_COS_EPSILON = 0.01;

    private BallAnimation ballAnimation;
    private int x;
    private int y;
    private Double direction;

    public BallAnimationBroker ballAnimation(BallAnimation ballAnimation) {
        this.ballAnimation = ballAnimation;
        return this;
    }

    public BallAnimationBroker x(int x) {
        this.x = x;
        return this;
    }

    public BallAnimationBroker y(int y) {
        this.y = y;
        return this;
    }

    public BallAnimationBroker direction(double direction) {
        this.direction = direction;
        return this;
    }

    public BallAnimation build() {
        this.ballAnimation = new BallAnimation();
        this.ballAnimation.setTime(Double.MAX_VALUE);
        this.ballAnimation.setEndTimestamp(Long.MAX_VALUE);
        if (this.direction != null) {
            updateValues(this.x, this.y, this.direction);
        } else {
            this.ballAnimation.setStartX(this.x);
            this.ballAnimation.setEndX(this.x);
            this.ballAnimation.setStartY(this.y);
            this.ballAnimation.setEndY(this.y);
        }
        return this.ballAnimation;
    }

    /**
     * Check if there is a collision and updates the ball animation
     *
     * @param  players All the players in the game
     * @return         a {@link BallCollisionResult} if no collision detected, null
     *                 otherwise
     */
    public BallCollisionResult collision(Iterable<Player> players) {
        final int startX = this.ballAnimation.getStartX();
        final int startY = this.ballAnimation.getStartY();
        final CollisionContext cc = new CollisionContext();

        cc.endX = this.ballAnimation.getEndX();
        cc.endY = this.ballAnimation.getEndY();

        // direction inversion because Y increase upward, while top downward
        cc.direction = Math.atan2((double) -cc.endY + startY, (double) cc.endX - startX);

        boolean collision = false;
        // Collision detection with walls
        if (cc.endY == MIN_Y || cc.endY == MAX_Y) {
            cc.direction = -cc.direction;
            collision = true;
        }

        // Collision detection with players
        if (cc.endX == MIN_X || cc.endX == MAX_X) {
            collision = StreamSupport.stream(players.spliterator(), false) //
                    .anyMatch(p -> matchPlayer(p, cc));
        }

        if (!collision) {
            return cc.endX == MAX_X ? LEFT_TEAM_POINT : RIGHT_TEAM_POINT;
        }
        updateValues(cc.endX, cc.endY, cc.direction);
        return null;
    }

    private boolean matchPlayer(Player p, CollisionContext cc) {
        final int playerPosX = p.getTeam() == Player.Team.LEFT //
                ? PLAYER_LEFT_X //
                : PLAYER_RIGHT_X;
        if (Math.abs(playerPosX - cc.endX) > BALL_RADIUS) {
            return false;
        }
        final int deltaV = Math.abs(cc.endY - p.getY());
        if (deltaV > BALL_RADIUS + PLAYER_HEIGHT / 2) {
            return false;
        }
        if (deltaV <= PLAYER_HEIGHT / 2 - PLAYER_RADIUS) {
            // Horizontal collision
            cc.direction = (Math.PI - cc.direction) % (2 * Math.PI);
        } else {
            // Corner collision
            final int dY = cc.endY > p.getY() //
                    ? cc.endY - (p.getY() + PLAYER_HEIGHT / 2 - PLAYER_RADIUS)
                    : cc.endY - (p.getY() - PLAYER_HEIGHT / 2 + PLAYER_RADIUS);
            final int dX = cc.endX - playerPosX;
            // Normal of the collision plane
            cc.direction = Math.atan2(-dY, dX);
        }
        return true;
    }

    private void updateValues(int x, int y, double direction) {
        // Collision prediction
        final UpdateContext uc = new UpdateContext();
        uc.cos = Math.cos(direction);
        uc.sin = Math.sin(direction);

        if (Math.abs(uc.sin) < SIN_COS_EPSILON) {
            uc.nextX = uc.cos > 0 ? MAX_X : MIN_X;
            uc.nextY = y;
        } else if (Math.abs(uc.cos) < SIN_COS_EPSILON) {
            uc.nextX = x;
            uc.nextY = uc.sin > 0 ? MIN_Y : MAX_Y;
        } else {
            calcDiagonalNext(x, y, direction, uc);
        }

        final int collisionDistX = uc.nextX - x;
        final int collisionDistY = uc.nextY - y;
        final double collisionDist = Math.sqrt( //
                (double) collisionDistX * collisionDistX //
                        + collisionDistY * collisionDistY //
        );
        final double collisionTime = collisionDist / BALL_SPEED;
        final long collisionTimeMs = (long) (collisionTime * 1000);

        this.ballAnimation.setStartX(x);
        this.ballAnimation.setStartY(y);
        this.ballAnimation.setEndX(uc.nextX);
        this.ballAnimation.setEndY(uc.nextY);
        this.ballAnimation.setTime(collisionTime);
        this.ballAnimation.setEndTimestamp(System.currentTimeMillis() + collisionTimeMs);
    }

    private void calcDiagonalNext(int x, int y, double direction, UpdateContext uc) {
        final double m = Math.tan(-direction);
        final double q = y - m * x;
        // Y = mX + q

        final int wallX = uc.cos > 0 ? MAX_X : MIN_X;
        final int wallY = uc.sin > 0 ? MIN_Y : MAX_Y;

        final int wallDistY = uc.sin > 0 ? y - MIN_Y : MAX_Y - y;

        final int intersectionY = (int) (m * wallX + q);
        if (Math.abs(intersectionY - y) <= wallDistY) {
            uc.nextX = wallX;
            uc.nextY = intersectionY;
        } else {
            uc.nextX = (int) ((wallY - q) / m);
            uc.nextY = wallY;
        }
    }

    private static class CollisionContext {
        private int endX;
        private int endY;
        private double direction;
    }

    private static class UpdateContext {
        private double cos;
        private double sin;
        private int nextX;
        private int nextY;
    }
}
