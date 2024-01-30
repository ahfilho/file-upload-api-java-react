package br.com.api.link.generator;


import br.com.api.entity.Cpu;
import br.com.api.interfaces.FileLinkCreatorCpu;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/url/cpu")
public class BuildFileLinkCpu implements FileLinkCreatorCpu {

    @GetMapping("/files/{id}")
    public Cpu linkFile(Cpu cpu) {
        String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/cpu/").path(String.valueOf(cpu.getId()))
                .toUriString();
        cpu.setId(cpu.getId());
        cpu.setUrl(download);
        return cpu;

    }
}