package br.com.api;

import br.com.api.entity.Authority;
import br.com.api.entity.User;
import br.com.api.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
//(exclude = {SecurityAutoConfiguration.class })  //remove password default of spring security = (exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
public class ApiApplication implements CommandLineRunner {

    /* Iniciado por Arlindo L. */

    // Agendar tarefas para exibir determinado recurso no horario definido
    // Scheduling
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);


    }

    @PostConstruct
    protected void init() {
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(createAuthorithy("USER", "User role"));
        authorityList.add(createAuthorithy("ADMIN", "Admin role"));

        User user = new User();
        user.setUserName("user");
        user.setFirstName("arlindo");
        user.setLastName("lima");

        user.setPassword(passwordEncoder.encode("123456"));
        user.setEnable(true);
        userDetailsRepository.save(user);
        user.setAuthorities(authorityList);
    }

    private Authority createAuthorithy(String roleCode, String roleDescription) {
        Authority authority = new Authority();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
