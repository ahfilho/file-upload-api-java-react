package br.com.api.controller;

import br.com.api.entity.CustomerSale;
import br.com.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public CustomerSale save(@RequestBody CustomerSale customerSale) {
        return customerService.save(customerSale);
    }

    @GetMapping
    public List<CustomerSale> list() {
        return customerService.listAll();
    }
    @PutMapping("/{id}")
    public CustomerSale update(@PathVariable Long id, @RequestBody CustomerSale customerSale) throws Exception {
        customerSale.setId(id);
        return customerService.update(customerSale);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) throws Exception {
        customerService.deleta(id);
        return HttpStatus.OK;
    }

}
