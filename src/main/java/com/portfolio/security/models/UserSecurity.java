package com.portfolio.security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserSecurity implements UserDetails {
    private final long id;
    private final String login;
    private final String password;
    private final Roles role;

    public static final UserSecurity USER_SECURITY_EMPTY =  new UserSecurity();

    private UserSecurity() {
        this.role = Roles.USER;
        this.password = "";
        this.login = "";
        this.id = 0;
    }

    public UserSecurity(long id, String role, String password, String login) {
        this.role = Roles.valueOf(role);
        this.password = password;
        this.login = login;
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return id;
    }
}
