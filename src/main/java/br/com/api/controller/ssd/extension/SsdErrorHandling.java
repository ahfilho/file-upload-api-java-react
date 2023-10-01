package br.com.api.controller.ssd.extension;

import br.com.api.entity.Ssd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SsdErrorHandling {


    public ResponseEntity<String> teste(String originalFilename, boolean success) {
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        String message = success
                ? String.format("Cadastro realizado com sucesso: %s", originalFilename)
                : String.format("Falha no cadastro: %s", originalFilename);

        return ResponseEntity.status(status).body(message);
    }

    public ResponseEntity<String> testeUPdate(String originalFilename, boolean sucess) {
        HttpStatus status = sucess ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        String message = sucess
                ? String.format("Atualizado com sucesso!")
                : String.format("Erro durante a atualização.");
        return ResponseEntity.status(status).body(message);
    }

    public void convertId(String id) {
        try {
            Ssd ssd = new Ssd();
            Long convertStringToLong = Long.parseLong(String.valueOf(id));
            ssd.setId(convertStringToLong);
        } catch (NumberFormatException e) {
            System.out.println("Alguns dados ainda podem conter Strings." + e.getMessage());
        }
    }
}
