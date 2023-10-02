package br.com.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.controller.ssd.extension.SsdControllerExtension;
import br.com.api.controller.ssd.extension.SsdErrorHandling;
import br.com.api.entity.SsdCategory;
import br.com.api.enume.CategoryEnum;
import br.com.api.exceptions.ErrorHandling;
import br.com.api.persistence.SsdPersistenceService;
import br.com.api.search.SearchSsd;
import br.com.api.storage.BuildFileLinkSsd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.Ssd;
import br.com.api.service.SsdService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ssd")
public class SsdController {

    private final JWTTokenHelper jwtTokenHelper;

    private final SsdService ssdService;

    private final BuildFileLinkSsd buildFileLink;

    private final SsdPersistenceService ssdPersistenceService;

    private final SearchSsd searchSsd;

    private final SsdErrorHandling ssdErrorHandling;

    private final ErrorHandling errorHandling;

    public SsdController(JWTTokenHelper jwtTokenHelper, SearchSsd searchSsd, SsdService ssdService, BuildFileLinkSsd buildFileLink, SsdPersistenceService ssdPersistenceService, SsdErrorHandling ssdErrorHandling, ErrorHandling errorHandling) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ssdService = ssdService;
        this.buildFileLink = buildFileLink;
        this.ssdPersistenceService = ssdPersistenceService;
        this.searchSsd = searchSsd;
        this.ssdErrorHandling = ssdErrorHandling;
        this.errorHandling = errorHandling;
    }

    @PostMapping
    public ResponseEntity<String> saveSsd(MultipartFile file, Ssd ssd, SsdCategory ssdCategory) {
        try {
            ssdPersistenceService.SsdPersistence(file, ssd, ssdCategory);
            ssdCategory.setSsdProductCategory(String.valueOf(CategoryEnum.SSD));
            return ssdErrorHandling.teste(file.getOriginalFilename(), true);
        } catch (Exception e) {
            return ssdErrorHandling.teste(file.getOriginalFilename(), false);
        }
    }

    @GetMapping
    public List<Ssd> listSsd() {
        return ssdService.serviceListAllSsd().stream().map(buildFileLink::linkForFileSsd).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSsd(String id, MultipartFile file, Ssd ssd, SsdCategory ssdCategory) throws Exception {
        ssdErrorHandling.convertId(id);
        try {
            this.ssdService.serviceUpdateSsd(ssd, file, ssdCategory);
            return ssdErrorHandling.testeUPdate(file.getOriginalFilename(), true);
        } catch (Exception e) {
            return ssdErrorHandling.testeUPdate(file.getOriginalFilename(), false);
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteSsd(@PathVariable Long id) throws Exception {
        try {
            ssdService.deleteProduct(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping("/redirect/{param}")
    public ResponseEntity<?> newResources(@PathVariable String param) throws Exception {
        SsdControllerExtension sec = new SsdControllerExtension(jwtTokenHelper, ssdService, searchSsd, errorHandling);

        if ("list".equals(param)) {
            List<String> list = sec.listDayOfSale();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            try {
                Long id = Long.parseLong(param);
                Ssd ssd = sec.searchForId(id);
                if (ssd != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(ssd);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nada encontrado para o ID: " + id);
                }
            } catch (NumberFormatException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetro inválido: " + param);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
            }
        }
    }

}