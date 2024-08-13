package com.portfolio.features.user_features.registration_user.controllers;

import com.portfolio.features.user_features.registration_user.services.RegistrationUserService;
import com.portfolio.models.User;
import com.portfolio.security.services.CaptchaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class RegistrationUserController {
    private final RegistrationUserService registrationUserService;
    private final CaptchaService captchaService;

    public RegistrationUserController(RegistrationUserService registrationUserService, CaptchaService captchaService) {
        this.registrationUserService = registrationUserService;
        this.captchaService = captchaService;
    }

    @GetMapping("/users/captcha")
    public ResponseEntity<byte[]> getCaptcha(HttpSession httpSession) throws IOException, NoSuchAlgorithmException {
        byte[] imageBytes = captchaService.generateCaptcha(httpSession.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/users")
    public Integer registrationNewUser(@RequestBody UserDto userDto, HttpSession session) {
        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        return registrationUserService.addNewUser(user, userDto.getPassword());
    }


}