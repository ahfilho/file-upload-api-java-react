package br.com.api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.entity.Sac;
import br.com.api.repository.SacRepository;

@Service
@Transactional
public class SacService {

    @Autowired
    private SacRepository repositorySac;

    public Sac saveSac(Sac sac) {
        return this.repositorySac.save(sac);

    }

    public List<Sac> sacList() {
        return this.repositorySac.findAll();
    }

    public Sac sacUpdate(Sac sac) throws Exception {

        Optional<Sac> smu = this.repositorySac.findById(sac.getId());

        if (smu.isPresent()) {
            Sac s = smu.get();
            s.setName(s.getName());
            s.setEmail(s.getEmail());
            s.setMessage(s.getMessage());
            s.setTelephone(s.getTelephone());
            return s;
        } else {
            throw new Exception("ERRO AO ATUALIZAR O SAC" + sac.getId());
        }
    }

    public void delete(Long id) throws Exception {

        Optional<Sac> smd = this.repositorySac.findById(id);
        if (smd.isPresent()) {
            this.repositorySac.delete(smd.get());
        } else {
            throw new Exception("ERRO AO DELETAR O ID" + id);
        }
    }

}
