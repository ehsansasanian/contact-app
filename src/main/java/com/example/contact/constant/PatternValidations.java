package com.example.contact.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternValidations {

    public static Boolean base(String value, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.find();
    }

    public static Boolean email(String value) {
        return base(value, PatternList.EMAIL);
    }
}
