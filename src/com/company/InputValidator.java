package com.company;

import java.util.regex.Pattern;

public class InputValidator {
    // TODO: add regex patterns
    private final String emailPattern = "";
    private final String phonePattern = "";
    private final String addressPattern = "";
    private final String namePattern = "";

    private final String[] patternList =
            new String[]{this.emailPattern, this.phonePattern, this.addressPattern, this.namePattern};

    public boolean isValidPattern(String userInput, int type){
        Pattern matchPattern = Pattern.compile(this.patternList[type]);
        return matchPattern.matcher(userInput).matches();
    }
}
