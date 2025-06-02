package com.serzhputovski.circles.exception;

public class CircleException extends Exception {
    public CircleException(String message) {
        super(message);
    }

    public CircleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircleException(Throwable cause) {
        super(cause);
    }

    public CircleException() {
        super();
    }
}
