package br.com.api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.entity.Contact;
import br.com.api.repository.SacRepository;

@Service
@Transactional
public class SacService {

    @Autowired
    private SacRepository repositorySac;

    public Contact saveSac(Contact sac) {
        return this.repositorySac.save(sac);

    }

    public List<Contact> sacList() {
        return this.repositorySac.findAll();
    }

    public Contact sacUpdate(Contact sac) throws Exception {

        Optional<Contact> smu = this.repositorySac.findById(sac.getId());

        if (smu.isPresent()) {
            Contact s = smu.get();
            s.setName(s.getName());
            s.setEmail(s.getEmail());
            s.setMessage(s.getMessage());
            s.setPhone(s.getPhone());
            return s;
        } else {
            throw new Exception("ERRO AO ATUALIZAR O SAC" + sac.getId());
        }
    }

    public void delete(Long id) throws Exception {

        Optional<Contact> smd = this.repositorySac.findById(id);
        if (smd.isPresent()) {
            this.repositorySac.delete(smd.get());
        } else {
            throw new Exception("ERRO AO DELETAR O ID" + id);
        }
    }

}
