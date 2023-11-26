package it.unipr.soi23.game_web_server.model;

import java.io.Serial;

public class GameWebServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public GameWebServerException() {
        // empty
    }

    public GameWebServerException(String message) {
        super(message);
    }

    public GameWebServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameWebServerException(Throwable cause) {
        super(cause);
    }
}
