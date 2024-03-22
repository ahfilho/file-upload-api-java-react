package br.com.api.search;

import br.com.api.auth.token.JWTTokenHelper;
import br.com.api.repository.RamRepository;
import br.com.api.service.RamService;
import br.com.api.link.generator.BuildFileLinkRam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RamSearch {

    private final JWTTokenHelper jwtTokenHelper;

    private final RamService ramService;

    private final BuildFileLinkRam buildFileLinkRam;
    private final RamRepository ramRepository;


    public RamSearch(JWTTokenHelper jwtTokenHelper, RamService ramService, BuildFileLinkRam buildFileLinkRam,
                     RamRepository ramRepository) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ramService = ramService;
        this.buildFileLinkRam = buildFileLinkRam;
        this.ramRepository = ramRepository;
    }

    public List<String> searchForIdRam(@PathVariable Long id) throws Exception {
        List<String> b = (List<String>) ramService.searchId(id);
        return b;
    }

    public List<String> listDayOfSaleRam() {
        List<String> r = ramService.dayOfSaleRam();
        return r;
    }
}
