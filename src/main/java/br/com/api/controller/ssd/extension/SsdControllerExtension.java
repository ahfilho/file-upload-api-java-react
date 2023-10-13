package br.com.api.controller.ssd.extension;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.Ssd;
import br.com.api.exceptions.ErrorHandling;
import br.com.api.search.SearchSsd;
import br.com.api.service.SsdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class SsdControllerExtension {


    private final JWTTokenHelper jwtTokenHelper;
    private final SsdService ssdService;
    private SearchSsd searchSsd;
    private final ErrorHandling errorHandling;

    public SsdControllerExtension(JWTTokenHelper jwtTokenHelper, SsdService ssdService, SearchSsd searchSsd, ErrorHandling errorHandling) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ssdService = ssdService;
        this.searchSsd = searchSsd;
        this.errorHandling = errorHandling;
    }

    public Ssd searchForId(@PathVariable Long id) throws Exception {
        Ssd ssd = ssdService.searchId(id);
        if (ssd != null) {
            return ssd;
        } else {
            return null;
        }
    }


    public List<String> listDayOfSale() {
        List<String> listSsd = ssdService.dayOfSale(); ////
        if (!listSsd.isEmpty()) {
            Ssd ssd = new Ssd();
            return Collections.singletonList(ssd.getSerialNumber() + "" + listSsd);
        }
        return listSsd;
    }





}

