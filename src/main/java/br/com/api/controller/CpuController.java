package br.com.api.controller;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.Category;
import br.com.api.entity.Cpu;
import br.com.api.enume.CategoryEnum;
import br.com.api.service.CpuService;
import br.com.api.storage.BuildFileLinkControllerCpu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cpu")
public class CpuController {

    private final JWTTokenHelper jwtTokenHelper;

    private final CpuService cpuService;

    private final BuildFileLinkControllerCpu buildFileLink;

    public CpuController(JWTTokenHelper jwtTokenHelper, CpuService cpuService, BuildFileLinkControllerCpu buildFileLink) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.cpuService = cpuService;
        this.buildFileLink = buildFileLink;

    }

    @ExceptionHandler
    @PostMapping
    public ResponseEntity<String> save(MultipartFile file, Cpu cpu, Category category) throws Exception {

        try {

            category.setProductCategory(CategoryEnum.CPU.name());
            saveCpuWithFile(file, cpu, category);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Cpu " + cpu.getModel() + "cadastrado com sucesso!") + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Erro no cadastro.") + file.getOriginalFilename());
        }
    }

    private void saveCpuWithFile(MultipartFile file, Cpu cpu, Category category) throws Exception {
        cpuService.save(cpu, file, category);
    }

    @GetMapping
    public List<Cpu> list() {
        return cpuService.listAll().stream().map(this::linkFile).collect(Collectors.toList());

    }

    private Cpu linkFile(Cpu cpu) {
        buildFileLink.linkFile(cpu);
        return cpu;
    }

    public Cpu link(Cpu cpu) {
        long c1 = cpu.getId();
        String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(Long.toString(c1)).toUriString();
        cpu.setId(cpu.getId());
        cpu.setUrl(download);
        return cpu;

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(String id, MultipartFile file, Cpu cpu, Category category) throws Exception {

        try {
            long convertStringToLong = Long.parseLong(id);
            System.out.println(convertStringToLong);
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


}
