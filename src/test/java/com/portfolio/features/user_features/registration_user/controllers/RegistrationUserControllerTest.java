package com.portfolio.features.user_features.registration_user.controllers;

import com.portfolio.features.user_features.registration_user.services.RegistrationUserService;
import com.portfolio.security.configuration.WebSecurityConfig;
import com.portfolio.security.repositories.SecurityRepository;
import com.portfolio.security.services.CaptchaService;
import com.portfolio.security.services.TokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(RegistrationUserController.class)
@Import({WebSecurityConfig.class})
class RegistrationUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CaptchaService captchaService;
    @MockBean
    private RegistrationUserService registrationUserService;
    @MockBean
    private TokenProvider tokenProvider;
    @MockBean
    private SecurityRepository repository;

    @Test
    void getCaptcha() throws Exception {
        byte [] array = {};
        when(captchaService.generateCaptcha(any())).thenReturn(array);
        mockMvc.perform(get("/users/captcha"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.IMAGE_JPEG));
    }

}