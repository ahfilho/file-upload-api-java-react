package br.com.api.oauth;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/oauth")
public class OauthController {

    @GetMapping
    public String home() {
        return "teste";

    }

}
