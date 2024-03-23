package com.portfolio.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl implements JWTService{
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;


    public JWTServiceImpl(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String createJWTToken(String login, String password) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(login, password);
        var authUser = authenticationManager.authenticate(userNamePassword);
        return tokenProvider.generateAccessToken((UserDetails) authUser.getPrincipal());
    }
}
