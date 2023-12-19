package br.com.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Data
public class ImgSsdDto {

    @NotBlank
    private String fileName;

    @NotBlank
    private String contentType;

    @NotBlank
    private Long fileSize;

    @NotBlank
    private String urlSsd;

    @NotBlank
    private byte[] data;

}
