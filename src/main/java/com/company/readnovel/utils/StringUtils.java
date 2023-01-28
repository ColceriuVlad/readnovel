package com.company.readnovel.utils;

import com.company.readnovel.exceptions.NotValidatedException;
import org.springframework.stereotype.Component;

@Component
public class StringUtils {
    public void validatePropertyFormat(String propertyName, String propertyValue) {
        char ch;
        var upperCaseFlag = false;
        var lowerCaseFlag = false;
        var numberFlag = false;

        isNotNullOrEmpty(propertyName, propertyValue);

        var minSize = 7;
        if (propertyValue.length() < minSize) {
            var errorMessage = propertyName + " must have at least 7 characters";
            throw new NotValidatedException(errorMessage);
        }

        for (int i = 0; i < propertyValue.length(); i++) {
            ch = propertyValue.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                upperCaseFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if (numberFlag && upperCaseFlag && lowerCaseFlag)
                return;
        }
        var errorMessage = propertyName + " must contains 1 upper case character, 1 lower case character and 1 number";
        throw new NotValidatedException(errorMessage);
    }

    public void isValidEmail(String propertyName, String propertyValue) {
        isNotNullOrEmpty(propertyName, propertyValue);

        if (!propertyValue.contains("@") || !propertyValue.contains(".com")) {
            var errorMessage = propertyName + " does not respect the email validations";
            throw new NotValidatedException(errorMessage);
        }
    }

    public void isNotNullOrEmpty(String propertyName, String propertyValue) {
        if (propertyValue == null) {
            throw new NotValidatedException(propertyName + " cannot be null");
        }

        if(propertyValue.isEmpty()){
            throw new NotValidatedException(propertyName + " cannot be empty");
        }
    }
}
