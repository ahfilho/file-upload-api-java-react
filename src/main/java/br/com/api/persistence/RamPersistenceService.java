package br.com.api.persistence;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.ProductCategoryRam;
import br.com.api.entity.Ram;
import br.com.api.service.RamService;
import br.com.api.link.generator.BuildFileLinkRam;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class RamPersistenceService {

    private final JWTTokenHelper jwtTokenHelper;
    private final RamService ramService;
    private final BuildFileLinkRam buildFileLinkRam;

    public RamPersistenceService(JWTTokenHelper jwtTokenHelper, RamService ramService, BuildFileLinkRam buildFileLinkRam) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ramService = ramService;

        this.buildFileLinkRam = buildFileLinkRam;
    }

    public void RamPersistence(MultipartFile file, Ram ram, ProductCategoryRam productCategory) throws IOException {

        ramService.ramSave(ram, file, productCategory);
    }
}
