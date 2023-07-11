package br.com.api.controller;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.Category;
import br.com.api.entity.Cpu;
import br.com.api.enume.CategoryEnum;
import br.com.api.persistence.CpuPersistenceController;
import br.com.api.search.SearchCpu;
import br.com.api.service.CpuService;
import br.com.api.storage.BuildFileLinkCpu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cpu")
public class CpuController {

    private final JWTTokenHelper jwtTokenHelper;

    private final CpuService cpuService;

    private final BuildFileLinkCpu buildFileLink;

    private final CpuPersistenceController cpuPersistenceController;

    private final SearchCpu searchCpu;

    public CpuController(SearchCpu searchCpu, JWTTokenHelper jwtTokenHelper, CpuService cpuService, BuildFileLinkCpu buildFileLink, CpuPersistenceController cpuPersistenceController) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.cpuService = cpuService;
        this.buildFileLink = buildFileLink;
        this.cpuPersistenceController = cpuPersistenceController;
        this.searchCpu = searchCpu;
    }

    @ExceptionHandler
    @PostMapping
    public ResponseEntity<String> save(MultipartFile file, Cpu cpu, Category category) throws Exception {

        try {
            category.setProductCategory(CategoryEnum.CPU.name());
            cpuPersistenceController.saveCpuWithFile(file, cpu, category);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Cpu " + cpu.getModel() + "cadastrado com sucesso!") + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Erro no cadastro.") + file.getOriginalFilename());
        }
    }

    @GetMapping
    public List<Cpu> listWhitFileLink() {
        return cpuService.listAll().stream().map(buildFileLink::linkFile).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(String id, MultipartFile file, Cpu cpu, Category category) throws Exception {

        try {
            long convertStringToLong = Long.parseLong(id);
            cpu.setId(convertStringToLong);
        } catch (NumberFormatException e) {
            System.out.println("Alguns dados ainda podem conter Strings." + e.getMessage());
        }
        try {
            category.setProductCategory(CategoryEnum.SSD.name());
            this.cpuService.update(file, category, cpu);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Atualizado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Erro durante a atualização."));
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) throws Exception {
        cpuService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public Cpu searchIdCpu(@PathVariable Long id) throws Exception {
        return searchCpu.searchForCpuId(id);
    }

}
