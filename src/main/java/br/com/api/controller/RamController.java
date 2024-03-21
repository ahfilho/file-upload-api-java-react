package br.com.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.api.auth.token.JWTTokenHelper;
import br.com.api.controller.ram.extension.RamControllerExtension;
import br.com.api.controller.ssd.extension.RamErrorHandling;
import br.com.api.dto.ProductCategoryRamDto;
import br.com.api.dto.RamDto;
import br.com.api.entity.ProductCategoryRam;
import br.com.api.persistence.RamPersistenceService;
import br.com.api.search.RamSearch;
import br.com.api.link.generator.BuildFileLinkRam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.api.entity.Ram;
import br.com.api.service.RamService;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ram")
public class RamController extends ProductController<Ram> {

    private final JWTTokenHelper jwtTokenHelper;

    private final RamService ramService;

    private final RamPersistenceService ramPersistenceService;

    private final RamErrorHandling ramErrorHandling;

    private final BuildFileLinkRam buildFileLinkRam;

    private final RamSearch ramSearch;

    public RamController(JWTTokenHelper jwtTokenHelper, RamService ramService, RamPersistenceService ramPersistenceService, RamErrorHandling ramErrorHandling, BuildFileLinkRam buildFileLinkRam, RamSearch ramSearch) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ramService = ramService;
        this.ramPersistenceService = ramPersistenceService;
        this.ramErrorHandling = ramErrorHandling;
        this.buildFileLinkRam = buildFileLinkRam;
        this.ramSearch = ramSearch;
    }

    @PostMapping
    public ResponseEntity<String> ramSave(@RequestParam("file") MultipartFile file, RamDto ramDto, ProductCategoryRamDto productCategoryDto) {

        try {
            ModelMapper mp = new ModelMapper();
            ProductCategoryRam productCategoryRam = mp.map(productCategoryDto, ProductCategoryRam.class);
            Ram ram = mp.map(ramDto, Ram.class);
            ramPersistenceService.RamPersistence(file, ram, productCategoryRam);
            return ramErrorHandling.saveErrorHandling(file.getOriginalFilename(), true);
        } catch (Exception e) {
            return ramErrorHandling.saveErrorHandling(file.getOriginalFilename(), false);
        }
    }

    @GetMapping
    public List<Ram> listRam() {
        return ramService.ramList().stream().map(buildFileLinkRam::linkForFileRam).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> ramUpdate(String id, MultipartFile file, Ram ram, ProductCategoryRam productCategoryRam) throws Exception {
        ramErrorHandling.convertRamId(id);
        try {
            this.ramService.ramUpdateService(ram, file, productCategoryRam);
            return ramErrorHandling.updateErrorHandling(file.getOriginalFilename(), true);
        } catch (Exception e) {
            return ramErrorHandling.updateErrorHandling(file.getOriginalFilename(), false);
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus ramDelete(@PathVariable Long id) throws Exception {
        this.ramService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("redirect/{param}")
    public ResponseEntity<?> newResourcesForRam(@PathVariable String param) throws Exception {
        RamControllerExtension rec = new RamControllerExtension(jwtTokenHelper, ramService, ramSearch, ramErrorHandling);
        if ("list".equals(param)) {
            List<String> list = rec.listDayOfSaleRam();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            try {
                Long id = Long.parseLong(param);
                Ram ram = rec.searchForIdRam(id);
                if (ram != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(ram);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nada encontrado para o ID" + id);
                }
            } catch (NumberFormatException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetro inválido:" + param);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
            }
        }
    }
}








