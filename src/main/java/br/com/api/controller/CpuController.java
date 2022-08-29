package br.com.api.controller;

import br.com.api.entity.Category;
import br.com.api.entity.Cpu;
import br.com.api.service.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/cpu")
public class CpuController {

    @Autowired
    private CpuService cpuService;

    @GetMapping
    public List<Cpu> list() {
        return cpuService.listAll();

    }

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("file") MultipartFile file, Cpu cpu, Category category) {
        try {
            cpuService.save(cpu,file,category);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("SUCESS") + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("ERROR") + file.getOriginalFilename());
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) throws Exception {
        cpuService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cpu> cpuUpdate(@PathVariable Long id, @RequestBody Cpu cpu) throws Exception {
        cpu.setId(id);
        return ResponseEntity.ok().body(this.cpuService.update(cpu));

    }
}
