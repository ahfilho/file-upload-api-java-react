package br.com.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductCategoryRamDto {

    @NotBlank
    private String productCategory;
}