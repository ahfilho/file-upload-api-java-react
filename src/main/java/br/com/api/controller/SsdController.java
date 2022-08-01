package br.com.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.entity.Category;
import br.com.api.entity.Ssd;
import br.com.api.service.SsdService;

@RestController
@RequestMapping("/ssd")
public class SsdController {

    @Autowired
    private SsdService ssdService;

    @PostMapping
    public ResponseEntity<String> productSaveUploadImgCategory(@RequestParam("file") MultipartFile file,
                                                               Ssd ssd, Category category) {
        try {
            ssdService.saveProductFileCategory(ssd, file, category);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("sucesso no upload %s", file.getOriginalFilename()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("Falha no upload %s", file.getOriginalFilename()));
        }
    }

    @GetMapping
    public List<Ssd> list() {
        return ssdService.listAllSsd().stream().map(this::linkImg).collect(Collectors.toList());
    }

    private Ssd linkImg(Ssd ssd) {
        long l1 = ssd.getId();
        String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(Long.toString(l1))
                .toUriString();

        Ssd s = new Ssd();
        ssd.setId(ssd.getId());
        ssd.setUrl(download);

        return ssd;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id) throws Exception {
        ssdService.deleteProduct(id);
        return HttpStatus.OK;
    }
}