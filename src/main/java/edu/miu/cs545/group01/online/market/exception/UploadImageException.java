package edu.miu.cs545.group01.online.market.exception;

public class UploadImageException extends RuntimeException {

    public UploadImageException(String message) {
        super(message);
    }

    public UploadImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
