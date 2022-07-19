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

    public Sac saveSac(Sac sacModel) {
        return this.repositorySac.save(sacModel);

    }

    public List<Sac> sacList() {
        return this.repositorySac.findAll();
    }

    public Sac sacUpdate(Sac sacModel) throws Exception {

        Optional<Sac> smu = this.repositorySac.findById(sacModel.getId());

        if (smu.isPresent()) {
            Sac sac = smu.get();
            sac.setName(sacModel.getName());
            sac.setEmail(sacModel.getEmail());
            sac.setMessage(sacModel.getMessage());
            sac.setTelephone(sacModel.getTelephone());
            return sac;
        } else {
            throw new Exception("ERRO AO ATUALIZAR O SAC" + sacModel.getId());
        }
    }

    public void delete(Long sacId) throws Exception {
        {
            Optional<Sac> smd = this.repositorySac.findById(sacId);
            if (smd.isPresent()) {
                this.repositorySac.delete(smd.get());
            } else {
                throw new Exception("ERRO AO DELETAR O ID" + sacId);
            }

        }
    }

}
