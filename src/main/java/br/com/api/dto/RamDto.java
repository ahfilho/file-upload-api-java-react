package br.com.api.dto;

import br.com.api.entity.ImgRam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RamDto {


    @NotBlank
    private String brand;

    @NotBlank
    private int mhz;

    @NotBlank
    private int size;

    @NotBlank
    private String serialNumber;

    @NotBlank
    private float purchasePrice;

    @NotBlank
    private String purchaseDate;
    @NotBlank
    private float saleValue;

    @NotBlank
    private String arrivalDate;

    @NotBlank
    private String model;

    @NotBlank
    private String url;

    @NotBlank
    private ImgRam imgRam;

}















