package br.com.api.service;

import br.com.api.entity.Sale;
import br.com.api.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> listAll() {
        return saleRepository.findAll();
    }

    public Sale update(Long id) throws Exception {
        Optional<Sale> b = saleRepository.findById(id);
        if (b.isPresent()) {
            Sale sale = b.get();
            sale.setCodeSale(sale.getCodeSale());
            sale.setTypeSale(sale.getTypeSale());


            return sale;

        } else {
            throw new Exception("ERRO");
        }
    }

    public void delete(Long id) throws Exception {
        Optional<Sale> del = saleRepository.findById(id);
        if (del.isPresent()) {
            saleRepository.delete(del.get());
        } else {
            throw new Exception("ERRO AO DELETAR" + id);
        }
    }
}
