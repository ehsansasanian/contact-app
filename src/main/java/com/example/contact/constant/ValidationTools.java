package com.example.contact.constant;

import com.example.contact.exceptionhandler.FieldValidationException;

public class ValidationTools {

    public static void email(String value, Object fieldName) throws FieldValidationException {
        if (!PatternValidations.email(value))
            throw new FieldValidationException(fieldName.toString(), value);
    }
}
