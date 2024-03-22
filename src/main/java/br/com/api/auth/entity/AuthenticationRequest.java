package br.com.api.auth.entity;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String userName;
    private String password;


}
