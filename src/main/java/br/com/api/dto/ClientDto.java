package br.com.api.dto;

import br.com.api.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ClientDto extends Client {

    private Long id;
    private String name;
    private String email;
    private String contact;
    private String cpf;

}