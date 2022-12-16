package br.com.api.controller;

import br.com.api.entity.Category;
import br.com.api.entity.Cpu;
import br.com.api.enume.CategoryEnum;
import br.com.api.service.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/cpu")
public class CpuController {

    @Autowired
    private CpuService cpuService;

    @ExceptionHandler
    @GetMapping
    public List<Cpu> list() {
        return cpuService.listAll().stream().map(this::link).collect(Collectors.toList());

    }

    public Cpu link(Cpu cpu) {
        long c1 = cpu.getId();
        String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(Long.toString(c1)).toUriString();
        cpu.setId(cpu.getId());
        cpu.setUrl(download);
        return cpu;

    }

    @ExceptionHandler
    @PostMapping
    public ResponseEntity<String> save(@RequestParam("file") MultipartFile file, Cpu cpu, Category category) {
        try {
            category.setProductCategory(CategoryEnum.CPU.name());
            cpuService.save(cpu, file, category);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Cpu " + cpu.getModel() + "cadastrado com sucesso!") + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Erro no cadastro.") + file.getOriginalFilename());
        }
    }


    @ExceptionHandler
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) throws Exception {
        cpuService.delete(id);
        return HttpStatus.OK;
    }
    @ExceptionHandler
    @PutMapping("/{id}")
    public ResponseEntity<Cpu> cpuUpdate(@PathVariable Long id, @RequestBody Cpu cpu) throws Exception {
        cpu.setId(id);
        return ResponseEntity.ok().body(this.cpuService.update(cpu));

    }
}
