package com.company.readnovel.validator;

import com.company.readnovel.model.Role;
import com.company.readnovel.utils.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator {
    private StringUtils stringUtils;
    private Logger logger;

    public RoleValidator(StringUtils stringUtils, Logger logger) {
        this.stringUtils = stringUtils;
        this.logger = logger;
    }

    public void validateRole(Role role) {
        stringUtils.isNotNullOrEmpty("Name", role.name);
        logger.info("Successfully validated role");
    }
}
