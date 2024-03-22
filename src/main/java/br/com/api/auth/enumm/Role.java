package br.com.api.auth.enumm;

import org.springframework.security.core.parameters.P;

public enum Role {

ROLE_ADMIN("ADMIN"),
ROLE_USER("USUARIO");


    private final String role;

    Role(String role) {
        this.role = role;
    }
    public String getRole(){
        return  role;
    }
}
