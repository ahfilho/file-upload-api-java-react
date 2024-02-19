package br.com.api.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String nome;
    private String senha;

    public LoginDto(String jwtToken) {
    }
}
