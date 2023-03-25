package br.com.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
		//(exclude = {SecurityAutoConfiguration.class })  //remove password default of spring security = (exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
public class ApiApplication implements CommandLineRunner {

	/* Iniciado por Arlindo L. */

	// Agendar tarefas para exibir determinado recurso no horario definido
	// Scheduling

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);



	}

	@Override
	public void run(String... args) throws Exception {



		
	}
}
