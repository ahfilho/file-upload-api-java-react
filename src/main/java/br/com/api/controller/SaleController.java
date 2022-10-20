package br.com.api.controller;

import br.com.api.entity.Sale;
import br.com.api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public Sale save(Sale sale) {
        return saleService.save(sale);
    }

    @GetMapping
    public List<Sale> list() {
        return saleService.listAll();
    }

    @PutMapping("/{id}")
    public Sale update(@PathVariable Long id, @RequestBody Sale sale) throws Exception {
        sale.setId(id);
        return saleService.update(id);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) throws Exception {
        saleService.delete(id);
        return HttpStatus.OK;
    }

}
