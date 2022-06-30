package br.com.api.agendador;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class SchedulingTask {
	
 
	
	//@Scheduled(fixedRate = 5000)
	public void disparaTeste() {
		Date date = new Date();
		System.out.println("TESTE DO SCHEDULING"+date);
	}
	

}
