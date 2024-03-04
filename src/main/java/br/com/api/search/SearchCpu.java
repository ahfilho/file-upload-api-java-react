package br.com.api.search;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.Cpu;
import br.com.api.service.CpuService;
import br.com.api.link.generator.BuildFileLinkSsd;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SearchCpu {

    private final JWTTokenHelper jwtTokenHelper;

    private final BuildFileLinkSsd buildFileLink;

    private final CpuService cpuService;

    public SearchCpu(JWTTokenHelper jwtTokenHelper, CpuService cpuService, BuildFileLinkSsd buildFileLink) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.buildFileLink = buildFileLink;
        this.cpuService = cpuService;
    }

    public Cpu searchForCpuId(@PathVariable Long id) throws Exception {
        return cpuService.searchId(id);
    }
}
