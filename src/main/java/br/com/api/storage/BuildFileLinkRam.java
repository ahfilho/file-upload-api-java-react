package br.com.api.storage;

import br.com.api.entity.Ram;
import br.com.api.interfaces.FileLinkCreatorRam;
import br.com.api.interfaces.FileLinkCreatorSsd;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/url/ram")
public class BuildFileLinkRam implements FileLinkCreatorRam {


    @GetMapping("/files/{id}")
    public Ram linkForFileRam(Ram ram) {

        String downloadLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/ram/").path(String.valueOf(ram.getId()))
                .toUriString();
        ram.setId(ram.getId());
        ram.setUrl(downloadLink);
        return ram;

    }
}