package br.com.api.service;

import br.com.api.entity.CustomerSale;
import br.com.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public CustomerSale save(CustomerSale customerSale) {
        return this.customerRepository.save(customerSale);
    }

    public List<CustomerSale> listAll() {
        return this.customerRepository.findAll();
    }

    public CustomerSale update(CustomerSale customerSale) throws Exception {
        Optional<CustomerSale> cs = customerRepository.findById(customerSale.getId());
        if (cs.isPresent()) {
            CustomerSale c = cs.get();
            c.setName(customerSale.getName());
            c.setCpf(customerSale.getCpf());
            c.setContact(customerSale.getContact());
            c.setEmail(customerSale.getEmail());
            return c;
        } else {
            throw new Exception("Erro no update" + customerSale.getId());
        }
    }

    public void deleta(Long id) throws Exception {
        Optional<CustomerSale> ocs = customerRepository.findById(id);
        if (ocs.isPresent()) {
            customerRepository.delete(ocs.get());
        } else {
            throw new Exception("ERRO NO DELETE" + id);
        }
    }

}
