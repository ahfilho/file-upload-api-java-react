package br.com.api.controller.ssd.extension;

import br.com.api.entity.Ram;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RamErrorHandling {

    public ResponseEntity<String> saveErrorHandling(String originalFilename, boolean success) {
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        String message = success
                ? String.format("Cadastro realizado com sucesso:", originalFilename)
                : String.format("Falha no cadastro.");

        return ResponseEntity.status(status).body(message);

    }

    public ResponseEntity<String> updateErrorHandling(String originalFilename, boolean success) {
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        String message = success
                ? String.format("Atualizado com sucesso!")
                : String.format("Erro durante a atualização.");
        return ResponseEntity.status(status).body(message);
    }

    public void convertRamId(String id) {
        try {
            Ram ram = new Ram();
            Long convertStringToLong = Long.parseLong(String.valueOf(id));
            ram.setId(convertStringToLong);
        } catch (NumberFormatException e) {
            System.out.println("Alguns dados ainda podem conter Strings." + e.getMessage());

        }
    }
}
