package br.com.api.dto;

import br.com.api.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
public class AddressDto {

    private Long id;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String district;

    @NotBlank
    private String city;


}
