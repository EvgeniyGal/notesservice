package edu.goit.notesservice.auth;

import org.springframework.security.core.GrantedAuthority;

public enum AuthRole implements GrantedAuthority {

    ADMIN, USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
