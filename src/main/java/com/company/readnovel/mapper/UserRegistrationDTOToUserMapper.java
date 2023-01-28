package com.company.readnovel.mapper;

import com.company.readnovel.model.User;
import com.company.readnovel.model.dto.UserRegistrationDTO;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDTOToUserMapper {
    private Logger logger;

    public UserRegistrationDTOToUserMapper(Logger logger) {
        this.logger = logger;
    }

    public User getUser(UserRegistrationDTO userRegistrationDTO) {
        var user = new User();
        user.password = userRegistrationDTO.password;
        user.username = userRegistrationDTO.username;
        user.email = userRegistrationDTO.email;
        return user;
    }
}
