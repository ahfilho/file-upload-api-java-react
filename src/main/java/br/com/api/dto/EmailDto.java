package br.com.api.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class EmailDto {

    private Long userId;
    private String emailTo;
    private String subject;
    private String text;

}
