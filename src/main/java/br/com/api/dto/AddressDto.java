package br.com.api.dto;

import br.com.api.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto extends Address {

    private Long id;
    private String street;
    private String number;
    private String district;
    private String city;


}
