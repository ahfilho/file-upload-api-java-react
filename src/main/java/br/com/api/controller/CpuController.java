package br.com.api.controller;

import br.com.api.entity.Cpu;
import br.com.api.service.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Cpu> save(@RequestBody Cpu cpu) {
        return ResponseEntity.ok().body(this.cpuService.save(cpu));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) throws Exception {
        cpuService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Cpu> cpuUpdate(@PathVariable Long id, @RequestBody Cpu cpu) throws Exception{
        cpu.setId(id);
        return ResponseEntity.ok().body(this.cpuService.update(cpu));

    }
}
