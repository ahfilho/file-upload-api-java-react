package br.com.api.producer;


import br.com.api.dto.EmailDto;
import br.com.api.entity.Client;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientProducer {

    final RabbitTemplate rabbitTemplate;

    public ClientProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${email.broker.queue.email.name}")
    private String routingKey;

    public void sendMessageEmail(Client client) {
        var emailDto = new EmailDto();
        emailDto.setUserId(client.getId());
        emailDto.setEmailTo(client.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText("Olá, " + client.getName() + ", seja Bem-vindo(a)!" +
                "\nEm caso de dúvidas, suporte para produto ou de algum serviço, entre em contato pelo Whatsapp: 81 9 9650 1010");
        rabbitTemplate.convertAndSend("", routingKey, emailDto);

    }
}
