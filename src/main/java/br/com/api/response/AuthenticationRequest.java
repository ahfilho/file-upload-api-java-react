package br.com.api.response;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String userName;
    private String password;


}
