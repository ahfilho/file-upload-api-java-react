package br.com.api.auth.entity;

import lombok.Data;

@Data
public class UserInfo {


    private String firstName;
    private String lastName;
    private String userName;
    private Object roles;
}
