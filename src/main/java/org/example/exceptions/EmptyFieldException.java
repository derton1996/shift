package org.example.exceptions;

public class EmptyFieldException extends RuntimeException{

    public EmptyFieldException() {
        super("Не все поля заполнены");
    }
}
