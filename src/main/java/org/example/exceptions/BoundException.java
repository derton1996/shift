package org.example.exceptions;

public class BoundException extends RuntimeException {

    public BoundException(String date) {
        super("Заданной даты не существует: " + date);
    }
}
