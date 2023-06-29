package br.com.api.search;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.Ssd;
import br.com.api.service.SsdService;
import br.com.api.storage.BuildFileLinkSsd;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SearchSsd {


    private final JWTTokenHelper jwtTokenHelper;

    private final SsdService ssdService;
    private final BuildFileLinkSsd buildFileLink;

    public SearchSsd(JWTTokenHelper jwtTokenHelper, SsdService ssdService, BuildFileLinkSsd buildFileLink) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.ssdService = ssdService;
        this.buildFileLink = buildFileLink;
    }

    public Ssd searchForId(@PathVariable Long id) throws Exception {
        Ssd ssd = new Ssd();
        ssd.getId();
        return ssdService.searchId(id);
    }

    public List<String> listDayOfSale() {

        return ssdService.dayOfSale();
    }
}
