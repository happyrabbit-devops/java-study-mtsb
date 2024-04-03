package org.example.exception;

import java.util.Date;

public class InvalidAnimalException extends RuntimeException {
    public InvalidAnimalException(String message) {
        super(message + " " + new Date());
    }
}