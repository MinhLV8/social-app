package com.minhlv.socialappapi.exception;

public class HandledException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5737181373759608113L;

    private final int code;

    public HandledException(int code, String message) {
        super(message);
        this.code = code;
    }

    public HandledException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
