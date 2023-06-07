package br.com.api.interfaces;

import br.com.api.entity.Category;
import br.com.api.entity.Ssd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SsdControllerInterface {

    ResponseEntity<String> save(MultipartFile file, Ssd ssd, Category category);

    List<Ssd> list();

    ResponseEntity<String> update(String id, MultipartFile file, Ssd ssd, Category category) throws Exception;

    HttpStatus deleteProduct(Long id) throws Exception;

    Ssd searchForId(Long id) throws Exception;

    List<String> listDayOfSale();


}
