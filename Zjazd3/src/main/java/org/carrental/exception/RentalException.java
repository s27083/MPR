package org.carrental.exception;

public class RentalException extends RuntimeException {
    public RentalException(String message, Exception reason) {
        super(message,reason);
    }
}
