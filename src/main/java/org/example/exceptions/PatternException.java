package org.example.exceptions;

public class PatternException extends RuntimeException {

    public PatternException(String date) {
        super("Не соответствие шаблону ДД-ММ-ГГГГ: " + date);
    }
}
