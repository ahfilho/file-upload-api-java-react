package br.com.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.controller.ssd.extension.RamErrorHandling;
import br.com.api.entity.ProductCategory;
import br.com.api.persistence.RamPersistenceService;
import br.com.api.storage.BuildFileLinkRam;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.api.entity.Ram;
import br.com.api.service.RamService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ram")
public class RamController extends ProductController<Ram> {

    private final JWTTokenHelper jwtTokenHelper;

    private final RamService ramService;

    private final RamPersistenceService ramPersistenceService;

    private final RamErrorHandling ramErrorHandling;

    private final BuildFileLinkRam buildFileLinkRam;

    public RamController(JWTTokenHelper jwtTokenHelper, RamService ramService, RamPersistenceService ramPersistenceService, RamErrorHandling ramErrorHandling, BuildFileLinkRam buildFileLinkRam) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ramService = ramService;
        this.ramPersistenceService = ramPersistenceService;
        this.ramErrorHandling = ramErrorHandling;
        this.buildFileLinkRam = buildFileLinkRam;
    }

    @PostMapping
    public ResponseEntity<String> ramSave(@RequestParam("file") MultipartFile file, Ram ram, ProductCategory productCategory) {

        try {
            ramPersistenceService.RamPersistence(file, ram, productCategory);
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
    public ResponseEntity<Ram> ramUpdate(@PathVariable Long id, @NotNull @RequestBody Ram ram) throws Exception {
        ram.setId(id);
        return ResponseEntity.ok().body(this.ramService.ramUpdate(ram));
    }

    @DeleteMapping("/{id}")
    public HttpStatus ramDelete(@PathVariable Long id) throws Exception {
        this.ramService.delete(id);
        return HttpStatus.OK;
    }

    private Ram linkImgRam(Ram ram) {
        long r1 = ram.getId();
        String linkRam = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(Long.toString(r1)).toUriString();
        ram.setId(ram.getId());
        ram.setUrl(linkRam);
        return ram;
    }

}
