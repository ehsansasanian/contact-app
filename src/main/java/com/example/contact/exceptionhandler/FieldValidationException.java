package com.example.contact.exceptionhandler;

public class FieldValidationException extends RuntimeException {

    public FieldValidationException(String fieldName, String value) {
        super("FieldValidationException occurred, field name: " + fieldName + "  value :" + value);
    }

}
