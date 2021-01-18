package com.company;

import java.util.regex.Pattern;

public class InputValidator {
    private final String emailPattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@\\..com$";
    private final String phonePattern = "^[0-9]{10,12}$";
    private final String addressPattern = "(\\d+\\/?\\d+) [a-zA-Z_]+( [a-zA-Z_]+)*";
    private final String namePattern = "\\s+";

    private final String[] patternList =
            new String[]{this.emailPattern, this.phonePattern, this.addressPattern, this.namePattern};

    public boolean isValidPattern(String userInput, int type){
        Pattern matchPattern = Pattern.compile(this.patternList[type]);
        return matchPattern.matcher(userInput).matches();
    }
}
