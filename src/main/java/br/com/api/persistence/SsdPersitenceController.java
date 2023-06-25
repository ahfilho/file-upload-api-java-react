package br.com.api.persistence;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.Category;
import br.com.api.entity.Ssd;
import br.com.api.service.SsdService;
import br.com.api.storage.BuildFileLinkControllerSsd;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@AllArgsConstructor
@Controller
public class SsdPersitenceController {

    private final JWTTokenHelper jwtTokenHelper;

    private final SsdService ssdService;
    private final BuildFileLinkControllerSsd buildFileLink;

    public SsdPersitenceController(JWTTokenHelper jwtTokenHelper, SsdService ssdService, BuildFileLinkControllerSsd buildFileLink) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ssdService = ssdService;
        this.buildFileLink = buildFileLink;
    }

    public void saveSsdWithFileAndCategory(MultipartFile file, Ssd ssd, Category category) throws IOException {
        ssdService.saveProductFileCategory(ssd, file, category);

    }
}
