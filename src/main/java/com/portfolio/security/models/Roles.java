package com.portfolio.security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

import static com.portfolio.security.models.Permissions.*;

public enum Roles {
    OWNER(List.of(MODIFICATION_ADMIN, MODIFICATION_USER, MODIFICATION_CV)),
    ADMIN(List.of(MODIFICATION_USER)),
    USER(List.of(MODIFICATION_CV));

    private final List<Permissions> permissions;

    Roles(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<Permissions> getPermissions() {
        return this.permissions;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return this.permissions.stream()
                .map(x -> new SimpleGrantedAuthority(x.name()))
                .collect(Collectors.toList());
    }

}