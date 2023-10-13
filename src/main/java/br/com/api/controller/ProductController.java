package br.com.api.controller;

import br.com.api.entity.ProductCategorySsd;
import br.com.api.interfaces.Operations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController<T> implements Operations<T> {
    @Override
    public ResponseEntity<String> saveSsd(MultipartFile file, T product, ProductCategorySsd productCategorySsd) {
        return null;
    }

    @Override
    public List<T> listSsd() {
        return null;
    }

    @Override
    public ResponseEntity<String> updateSsd(String id, MultipartFile file, T product, ProductCategorySsd productCategorySsd) throws Exception {
        return null;
    }

    @Override
    public HttpStatus deleteSsd(Long id) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> newResources(String param) throws Exception {
        return null;
    }
}
