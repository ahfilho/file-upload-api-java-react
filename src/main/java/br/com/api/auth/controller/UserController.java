package br.com.api.auth.controller;

import br.com.api.auth.dto.ResetPasswordDto;
import br.com.api.auth.service.UserDetailsServiceImpl;
import br.com.api.auth.service.UserService;
import br.com.api.auth.dto.UserDto;
import br.com.api.auth.entity.User;
import br.com.api.auth.token.JWTTokenHelper;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/new")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, JWTTokenHelper jwtTokenHelper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveNewUser(@RequestBody @Validated UserDto userDto) {
        try {
            userService.save(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha no cadastro do usuário.");

        }
    }

    @GetMapping("/todos")
    public List<?> list()  {



//        if (principal != null) {
//            List<User> AllUsers = userService.listAll(null);
//            return ResponseEntity.ok(AllUsers);
//        }
//        UserDetailsServiceImpl userDetailsService = null;
//        User authenticatedUser = (User) userDetailsService.loadUserByUsername(principal.getName());
//
//        if (userService.isAdmin(authenticatedUser)) {
//            List<User> AllUsers = userService.listAll(authenticatedUser);
//            return ResponseEntity.ok(AllUsers);
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        return userService.listAll();


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> UserDelete(@PathVariable Long id) throws Exception {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído.");
    }

    @PutMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody @Validated ResetPasswordDto resetPasswordDto) {
        try {
            userService.resetPasswordFromUser(resetPasswordDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Senha alterada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao alterar a senha");
        }

    }
}
