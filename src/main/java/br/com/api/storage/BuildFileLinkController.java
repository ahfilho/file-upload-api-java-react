package br.com.api.storage;


import br.com.api.entity.Ssd;
import br.com.api.interfaces.FileLinkCreator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/url/")
public class BuildFileLinkController implements FileLinkCreator {


    @GetMapping("/files/{id}")
    public Ssd linkFile(Ssd ssd) {

        long l1 = ssd.getId();
        String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(Long.toString(l1))
                .toUriString();
        ssd.setId(ssd.getId());
        ssd.setUrl(download);
        return ssd;

    }
}
