package br.com.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductCategorySsdDto {


    @NotBlank
    private String productCategory;
}
