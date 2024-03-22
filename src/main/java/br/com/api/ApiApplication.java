package br.com.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
//(exclude = {SecurityAutoConfiguration.class })  //remove password default of spring security = (exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
public class ApiApplication implements CommandLineRunner {

    /* Iniciado por Arlindo L. */

    // Agendar tarefas para exibir determinado recurso no horario definido
    // Scheduling

//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserDetailsRepository userDetailsRepository;
//

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);

    }
//
//    @PostConstruct
//    protected void init() {
//        List<  Authority> authorityList = new ArrayList<>();
//
//        authorityList.add(createAuthorithy("USER", "User role"));
//        authorityList.add(createAuthorithy("ADMIN", "Admin role"));
//
//        var user = new User();
//        user.setUserName("dinho");
//        user.setFirstName("arlindo");
//        user.setLastName("lima");
//
//        user.setPassword(passwordEncoder.encode("arlindo123@56"));
//        user.setEnabled(true);
//        user.setAuthorities(authorityList);
//        System.out.println(user.getAuthorities()+"USER");
//        System.out.println(user.getAuthorities()+"ADM");
//
//
//        userDetailsRepository.save(user);
//
//    }
//
//
//    private Authority createAuthorithy(String roleCode, String roleDescription) {
//        Authority authority = new Authority();
//        authority.setRoleCode(roleCode);
//        authority.setRoleDescription(roleDescription);
//        return authority;
//    }

    @Override
    public void run(String... args) throws Exception {


    }
}
