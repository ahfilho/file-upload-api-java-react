package br.com.api.auth.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {

    private Long id;
    private String senha;
    private String email;
    private String nome;
}
