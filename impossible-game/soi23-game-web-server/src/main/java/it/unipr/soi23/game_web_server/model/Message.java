package it.unipr.soi23.game_web_server.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class Message {

    public enum Type {
        ERROR, //
        WARNING, //
        INFO;

        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }

    public enum Code {
        // Error
        GAME_NOT_FOUND, //
        PLAYER_NOT_FOUND, //
        PLAYER_ID_ALREADY_USED, //
        INVALID_PLAYER_TOKEN, //
        // INFO
        POINT_SCORED, //
        FLAG
    }

    private Type type;
    private Code code;
    private String customMessage;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Message type(Type type) {
        setType(type);
        return this;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Message code(Code code) {
        setCode(code);
        return this;
    }
    
    public String getCustomMessage() {
    	return customMessage;
    }
    
    public void setCustomMessage(String customMessage) {
    	this.customMessage = customMessage;
    }
    
    public Message customMessage(String customMessage) {
    	setCustomMessage(customMessage);
    	return this;
    }
}
