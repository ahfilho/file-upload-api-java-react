package br.com.api.dto;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SsdDto {


    @NotBlank
    private String brand;

    @NotBlank
    private String serialNumber;

    @NotBlank
    private String size;

    @NotBlank
    private String purchasePrice;

    @NotBlank
    private String purchaseDate;

    @NotNull
    private float saleValue;

    @NotBlank
    private String arrivalDate;

    @NotBlank
    private String url;

    @NotBlank
    private int amount;

    @NotBlank
    private String model;
}
