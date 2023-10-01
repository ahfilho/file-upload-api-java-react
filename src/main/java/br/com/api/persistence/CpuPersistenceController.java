package br.com.api.persistence;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.CpuCategory;
import br.com.api.entity.Cpu;
import br.com.api.service.CpuService;
import br.com.api.storage.BuildFileLinkSsd;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CpuPersistenceController {

    private final JWTTokenHelper jwtTokenHelper;

    private final CpuService cpuService;
    private final BuildFileLinkSsd buildFileLink;

    public CpuPersistenceController(JWTTokenHelper jwtTokenHelper, CpuService cpuService, BuildFileLinkSsd buildFileLink) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.cpuService = cpuService;
        this.buildFileLink = buildFileLink;
    }

    public void saveCpuWithFile(MultipartFile file, Cpu cpu, CpuCategory cpuCategory) throws Exception {
        cpuService.save(cpu, file, cpuCategory);
    }
}
