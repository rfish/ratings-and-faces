package com.randyfish.metadata.client;

/**
 * Smugmug exception
 */
public class SmugmugException extends RuntimeException {

    private int code;

    public SmugmugException() {
    }

    public SmugmugException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SmugmugException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmugmugException(Throwable cause) {
        super(cause);
    }

}
