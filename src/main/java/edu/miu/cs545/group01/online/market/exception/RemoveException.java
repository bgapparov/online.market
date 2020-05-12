package edu.miu.cs545.group01.online.market.exception;

public class RemoveException extends RuntimeException {

    public RemoveException(String message) {
        super(message);
    }

    public RemoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
