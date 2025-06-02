package com.serzhputovski.circles.exception;

public class CircleFileException extends Exception {
    public CircleFileException(String message) {
        super(message);
    }

    public CircleFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircleFileException(Throwable cause) {
        super(cause);
    }

    public CircleFileException() {
        super();
    }
}
