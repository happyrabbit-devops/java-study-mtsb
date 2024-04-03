package org.example.exception;

public class InvalidAnimalBirthDateException extends Exception {
    public InvalidAnimalBirthDateException(String message) {
        super(message);
    }
}
