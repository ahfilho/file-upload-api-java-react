package br.com.api.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.entity.Ram;
import br.com.api.repository.WhatsappRegistrationRepository;

@Service
@Transactional
public class RamService {

    @Autowired
    private WhatsappRegistrationRepository whatsRepository;

    public Ram whatsappSave(Ram ram) {

        Date dateAtual = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = df.format(dateAtual);
        ram.setArrivalDate(dateAtual);
        ram.setPurchaseDate(dateAtual);

        return this.whatsRepository.save(ram);

    }

    public List<Ram> whatsappList() {
        return this.whatsRepository.findAll();
    }

    public Ram whaytsappUpdate(Ram whats) throws Exception {
        Optional<Ram> whatsOptional = this.whatsRepository.findById(whats.getId());
        if (whatsOptional.isPresent()) {
            Ram wrm = whatsOptional.get();
            wrm.setBrand(whats.getBrand());
            wrm.setModel(whats.getModel());
            return wrm;
        } else {
            throw new Exception("ERRO AO ATUALIZAR O NUMERO DO WHATSAPP" + whats.getId());
        }
    }

    public void delete(Long whatsId) throws Exception {
        Optional<Ram> whats = this.whatsRepository.findById(whatsId);
        if (whats.isPresent()) {
            this.whatsRepository.delete(whats.get());
        } else {
            throw new Exception("ERRO AO DELETAR O ID" + whatsId);
        }
    }

}
