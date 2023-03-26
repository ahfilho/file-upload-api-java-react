package br.com.api.controller;

import br.com.api.entity.User;
import br.com.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/auth")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/new/user")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("DEU CERTO"));
    }

    @GetMapping
    public String list() {
        return "teste user auth";
    }

}
