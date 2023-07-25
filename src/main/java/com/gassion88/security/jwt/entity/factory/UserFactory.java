package com.gassion88.security.jwt.entity.factory;

import com.gassion88.security.jwt.dto.UserRegistrationDTO;
import com.gassion88.security.jwt.entity.User;

public class UserFactory {
    public User parseUserFromDTO(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUsername((userRegistrationDTO.getUsername()));
        user.setPassword(userRegistrationDTO.getPassword());
        user.setPassword(userRegistrationDTO.getEmail());

        return user;
    }
}
