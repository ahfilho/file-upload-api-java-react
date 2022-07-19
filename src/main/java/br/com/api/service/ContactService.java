package br.com.api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.entity.Client;
import br.com.api.repository.ContactRepository;

@Service
@Transactional
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Client contactSave(Client contactModel) {
        return this.contactRepository.save(contactModel);
    }

    public List<Client> contactList() {
        return this.contactRepository.findAll();
    }

    public Client updateContact(Client contactModel) throws Exception {
        Optional<Client> cmo = this.contactRepository.findById(contactModel.getId());
        if (cmo.isPresent()) {
            Client cm = cmo.get();
            cm.setName(contactModel.getName());
            cm.setEmail(contactModel.getEmail());
            cm.setSubject(contactModel.getSubject());
            cm.setTelephone(contactModel.getTelephone());
            return cm;
        } else {
            throw new Exception("ERRO AO ATUALIZAR" + contactModel.getId());
        }
    }

    public void contactDelete(Long contactId) throws Exception {
        Optional<Client> contact = this.contactRepository.findById(contactId);
        if (contact.isPresent()) {
            this.contactRepository.delete(contact.get());
        } else {
            throw new Exception("ERRO AO DELETAR O ID" + contactId);
        }
    }
}
