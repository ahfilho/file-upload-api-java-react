package br.com.api.auth.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {

    private Long id;
    private String password;
    private String email;
    private String userName;
    private String cpf;

}
