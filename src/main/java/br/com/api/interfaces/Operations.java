package br.com.api.interfaces;

import br.com.api.entity.ProductCategorySsd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Operations<T> {
    ResponseEntity<String> saveSsd(MultipartFile file, T product, ProductCategorySsd productCategorySsd);

    List<T> listSsd();

    ResponseEntity<String> updateSsd(String id, MultipartFile file,  T product,  ProductCategorySsd productCategorySsd) throws Exception;

    HttpStatus deleteSsd(Long id) throws Exception;

    ResponseEntity<?> newResources(String param) throws Exception;
}
