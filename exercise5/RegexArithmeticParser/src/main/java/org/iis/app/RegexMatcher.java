package org.iis.app;

import java.util.regex.Pattern;

public class RegexMatcher {

    private final static String PATTERN = "^[0-9]*+(\\*|\\/|\\+|\\-|\\^)[0-9]*+((\\*|\\/|\\+|\\-|\\^)[0-9]*)*";

    public boolean compute(String s){
       return Pattern.matches(PATTERN, s);
    }
}
