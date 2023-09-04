package com.bookingreviews.exception;

public class ActiveBookingsException extends RuntimeException {
    public ActiveBookingsException(String message) {
        super(message);
    }
}
