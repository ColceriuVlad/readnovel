package com.company.readnovel.validator;

import com.company.readnovel.model.User;
import com.company.readnovel.utils.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private Logger logger;
    private StringUtils stringUtils;

    public UserValidator(Logger logger, StringUtils stringUtils) {
        this.stringUtils = stringUtils;
        this.logger = logger;
    }

    public void validateUser(User user) {
        stringUtils.isValidEmail("Email", user.email);
        stringUtils.validatePropertyFormat("Username", user.username);
        stringUtils.validatePropertyFormat("Password", user.password);
        logger.info("Successfully validated user");
    }
}
