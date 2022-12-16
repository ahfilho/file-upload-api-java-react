
package br.com.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.api.entity.Ram;
import br.com.api.enume.CategoryEnum;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.entity.Category;
import br.com.api.entity.Ssd;
import br.com.api.service.SsdService;

import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ssd")
public class SsdController {

    @Autowired
    private SsdService ssdService;


    //TODO FAZER O METODO DE ZERAR O FORM DEPOIS DE SALVAR. O OUTRO ESTAVA DANDO ERRADO, ZERAVA ANTES DE SALVAR.

    @ExceptionHandler
    @PostMapping("/new")
    public ResponseEntity<String> ssdSave(@RequestParam("file") MultipartFile file,
                                          Ssd ssd, Category category) {
        try {
            category.setProductCategory(CategoryEnum.SSD.name());
            ssdService.saveProductFileCategory(ssd, file, category);
            return status(HttpStatus.OK)
                    .body(String.format("sucesso no cadastro: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return status(HttpStatus.OK)
                    .body(String.format("Falha no cadastro: %s", file.getOriginalFilename()));
        }
    }

    @GetMapping("/list")
    public List<Ssd> list() {
        return ssdService.listAllSsd().stream().map(this::linkImgSsd).collect(Collectors.toList());
    }

    private Ssd linkImgSsd(Ssd ssd) {
        long l1 = ssd.getId();
        String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(Long.toString(l1))
                .toUriString();
        ssd.setId(ssd.getId());
        ssd.setUrl(download);
        return ssd;

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ssd> updateSsd(@PathVariable Long id, @RequestBody Ssd ssd) throws Exception {
        ssd.getId();
        return ResponseEntity.ok().body(this.ssdService.update(ssd));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id) throws Exception {
        try {
            ssdService.deleteProduct(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

}