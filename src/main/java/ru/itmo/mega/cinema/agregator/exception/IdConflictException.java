package ru.itmo.mega.cinema.agregator.exception;

public class IdConflictException extends RuntimeException {

    public IdConflictException() {
        super();
    }

    public IdConflictException(String message) {
        super(message);
    }
}
