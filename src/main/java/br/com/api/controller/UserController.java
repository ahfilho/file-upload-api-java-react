package br.com.api.controller;

import br.com.api.entity.User;
import br.com.api.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {


    @Autowired
    private CustomUserService userService;

    @GetMapping
    public String list() {
        return "teste user auth";
    }

}
