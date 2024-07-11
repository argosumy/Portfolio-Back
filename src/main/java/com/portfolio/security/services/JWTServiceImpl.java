package com.portfolio.security.services;

import com.portfolio.security.controllers.TokenDto;
import com.portfolio.security.models.UserSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTServiceImpl implements JWTService{
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;


    public JWTServiceImpl(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenDto createJWTToken(String login, String password) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(login, password);
        var authUser = authenticationManager.authenticate(userNamePassword);
        String token = tokenProvider.generateAccessToken((UserDetails) authUser.getPrincipal());
        long userId = ((UserSecurity)authUser.getPrincipal()).getId();
        String userLogin = ((UserSecurity)authUser.getPrincipal()).getUsername();
        List<String> permissions = ((UserDetails) authUser.getPrincipal()).getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new TokenDto(userId, token, userLogin, permissions);
    }
}
