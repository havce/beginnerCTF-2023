package it.unipr.soi23.game_web_server.broker;

import static it.unipr.soi23.game_web_server.utils.Soi23GameWebServerConst.PLAYER_MAX_Y;
import static it.unipr.soi23.game_web_server.utils.Soi23GameWebServerConst.PLAYER_MIN_Y;

import it.unipr.soi23.game_web_server.model.Player;

public class PlayerBroker {

    private Player player;

    public PlayerBroker player(Player player) {
        this.player = player;
        return this;
    }

    /**
     * Compares the provided token with the originally saved one
     *
     * @param  token Token to compare
     * @return       true if the token is correct, false otherwise
     */
    public boolean checkPlayerToken(String token) {
        /*
        Return true if the token is correct (and not null), false otherwise
         */
    	return token != null && token.equals(player.getToken());
    }

    /**
     * Moves the player
     *
     * @param y The new position along the Y axis
     */
    public void moveToY(int y) {
        /*
        Set the new y and lastMovementTimestamp of the player only if:
        - the new value is between PLAYER_MIN_Y and PLAYER_MAX_Y
         */
    	
    	// clamp the value if it exceeded the limits
    	if(y < PLAYER_MIN_Y) y = PLAYER_MIN_Y;
    	if(y > PLAYER_MAX_Y) y = PLAYER_MAX_Y;
    	
    	player.setY(y);
    }
}
