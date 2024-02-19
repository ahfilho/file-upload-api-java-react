package br.com.api.auth.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cpf;
    private String profile;
}
