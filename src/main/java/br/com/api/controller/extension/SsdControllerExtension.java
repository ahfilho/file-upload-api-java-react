package br.com.api.controller.extension;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.controller.SsdController;
import br.com.api.entity.Ssd;
import br.com.api.search.SearchSsd;
import br.com.api.service.SsdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a")
public class SsdControllerExtension {


    private final JWTTokenHelper jwtTokenHelper;
    private SsdService ssdService;
    private SearchSsd searchSsd;

    public SsdControllerExtension(JWTTokenHelper jwtTokenHelper) {
        this.jwtTokenHelper = jwtTokenHelper;
    }


    @GetMapping("/search/{id}")
    public Ssd searchForId(@PathVariable Long id) throws Exception {
        return ssdService.searchId(id);
    }

    @GetMapping("/sale/day")
    public List<String> listDayOfSale() {
        return searchSsd.listDayOfSale();
    }
}

