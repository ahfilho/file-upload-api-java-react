package br.com.api.consumer;

import br.com.api.dto.EmailDto;
import br.com.api.entity.Email;
import br.com.api.enume.StatusEmail;
import br.com.api.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    final EmailService emailService;

    public Consumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${email.broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailDto emailDto) {
        Email email = new Email();
        ModelMapper mp = new ModelMapper();
        email = mp.map(emailDto, Email.class);
        emailService.sendEmail(email);

        if ("SENT".equalsIgnoreCase(String.valueOf(email.getStatusEmail()))) {
            System.out.println("E-mail enviado para: " + emailDto.getEmailTo());
        } else {
            System.out.println("Erro ao enviar o e-mail.");
        }
    }
}
