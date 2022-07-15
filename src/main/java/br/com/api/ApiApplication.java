package br.com.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableScheduling
public class ApiApplication implements CommandLineRunner {

	/* Iniciado por Arlindo L. */

	// Agendar tarefas para exibir determinado recurso no horario definido
	// Scheduling

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

	}
	// 123456

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}
}
