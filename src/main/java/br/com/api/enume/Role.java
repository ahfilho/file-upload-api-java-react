package br.com.api.enume;

import org.springframework.security.core.parameters.P;

public enum Role {

ROLE_ADMIN("ROLE_ADMIN"),
ROLE_USER("ROLE_USER");


    private final String role;

    Role(String role) {
        this.role = role;
    }
    public String getRole(){
        return  role;
    }
}
