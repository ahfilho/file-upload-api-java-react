package br.com.api.persistence;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.Cpu;
import br.com.api.entity.ProductCategoryCpu;
import br.com.api.repository.ProductCategoryRepositorySsd;
import br.com.api.service.CpuService;
import br.com.api.storage.BuildFileLinkSsd;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CpuPersistenceController {

    private final JWTTokenHelper jwtTokenHelper;

    private final CpuService cpuService;
    private final BuildFileLinkSsd buildFileLink;
    private final ProductCategoryRepositorySsd productCategoryRepositorySsd;

    public CpuPersistenceController(JWTTokenHelper jwtTokenHelper, CpuService cpuService, BuildFileLinkSsd buildFileLink, ProductCategoryRepositorySsd productCategoryRepositorySsd) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.cpuService = cpuService;
        this.buildFileLink = buildFileLink;
        this.productCategoryRepositorySsd = productCategoryRepositorySsd;
    }

    public void saveCpuWithFile(MultipartFile file, Cpu cpu, ProductCategoryCpu productCategoryCpu) throws Exception {
        cpuService.save(cpu, file, productCategoryCpu);
    }
}
