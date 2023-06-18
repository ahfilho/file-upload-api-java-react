package br.com.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.enume.CategoryEnum;
import br.com.api.persistence.SsdPersitenceController;
import br.com.api.storage.BuildFileLinkControllerSsd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.Category;
import br.com.api.entity.Ssd;
import br.com.api.service.SsdService;


import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ssd")
public class SsdController {

    private final JWTTokenHelper jwtTokenHelper;

    private final SsdService ssdService;

    private final BuildFileLinkControllerSsd buildFileLink;

    private final SsdPersitenceController ssdPersitenceController;

    public SsdController(JWTTokenHelper jwtTokenHelper, SsdService ssdService, BuildFileLinkControllerSsd buildFileLink, SsdPersitenceController ssdPersitenceController) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ssdService = ssdService;
        this.buildFileLink = buildFileLink;
        this.ssdPersitenceController = ssdPersitenceController;
    }

    @PostMapping
    public ResponseEntity<String> save(MultipartFile file, Ssd ssd, Category category) {

        try {
            category.setProductCategory(CategoryEnum.SSD.name());
            ssdPersitenceController.saveSsdWithFileAndCategory(file, ssd, category);
            return status(HttpStatus.OK)
                    .body(String.format("Cadastro realizado com sucesso.: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return status(HttpStatus.OK)
                    .body(String.format("Falha no cadastro: %s", file.getOriginalFilename()));
        }
    }

    @GetMapping
    public List<Ssd> list() {
        return ssdService.listAllSsd().stream().map(this::linkFile).collect(Collectors.toList());
    }

    private Ssd linkFile(Ssd ssd) {
        buildFileLink.linkFile(ssd);
        return ssd;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(String id, MultipartFile file, Ssd ssd, Category category) throws Exception {

        try {
            long convertStringToLong = Long.parseLong(id);
            System.out.println(convertStringToLong);
            ssd.setId(convertStringToLong);
        } catch (NumberFormatException e) {
            System.out.println("Alguns dados ainda podem conter Strings." + e.getMessage());
        }
        try {
            category.setProductCategory(CategoryEnum.SSD.name());
            this.ssdService.update(ssd, file, category);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Atualizado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Erro durante a atualização."));
        }
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

    @GetMapping("/{id}")
    public Ssd searchForId(@PathVariable Long id) throws Exception {
        Ssd ssd = new Ssd();
        ssd.getId();
        return ssdService.searchId(id);
    }

    @GetMapping("/sale/day")
    public List<String> listDayOfSale() {

        return ssdService.dayOfSale();
    }
}