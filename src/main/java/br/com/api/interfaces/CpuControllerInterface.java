package br.com.api.interfaces;

import br.com.api.entity.Category;
import br.com.api.entity.Cpu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CpuControllerInterface {


    ResponseEntity<String> save(MultipartFile file, Cpu cpu, Category category);

    List<Cpu> list();

    ResponseEntity<String> update(String id, MultipartFile file, Cpu cpu, Category category) throws Exception;

    HttpStatus delete(Long id) throws Exception;

}
