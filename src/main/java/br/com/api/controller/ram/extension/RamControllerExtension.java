package br.com.api.controller.ram.extension;


import br.com.api.auth.token.JWTTokenHelper;
import br.com.api.controller.ssd.extension.RamErrorHandling;
import br.com.api.entity.Ram;
import br.com.api.search.RamSearch;
import br.com.api.service.RamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class RamControllerExtension {

    private final JWTTokenHelper jwtTokenHelper;
    private final RamService ramService;
    private final RamSearch ramSearch;
    private final RamErrorHandling ramErrorHandling;

    public RamControllerExtension(JWTTokenHelper jwtTokenHelper, RamService ramService, RamSearch ramSearch, RamErrorHandling ramErrorHandling) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ramService = ramService;
        this.ramSearch = ramSearch;
        this.ramErrorHandling = ramErrorHandling;
    }

    public Ram searchForIdRam(@PathVariable Long id) throws Exception {
        Ram ram = ramService.searchId(id);
        return ram;
    }

    public List<String> listDayOfSaleRam() {
        List<String> listRam = ramService.dayOfSaleRam();
        if (!listRam.isEmpty()) {
            Ram ram = new Ram();
            return Collections.singletonList(ram.getSerialNumber() + " " + listRam);
        }
        return listRam;
    }
}








































