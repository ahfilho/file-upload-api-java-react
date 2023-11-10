package br.com.api.controller.ssd.extension;

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

}
