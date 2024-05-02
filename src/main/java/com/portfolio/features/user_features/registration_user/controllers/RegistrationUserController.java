package com.portfolio.features.user_features.registration_user.controllers;

import com.portfolio.features.user_features.registration_user.services.RegistrationUserService;
import com.portfolio.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationUserController {
    private final RegistrationUserService registrationUserService;

    public RegistrationUserController(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    @PostMapping("/users")
    public Integer registrationNewUser(@RequestBody UserDto userDto) {
        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        return registrationUserService.addNewUser(user, userDto.getPassword());
    }


}