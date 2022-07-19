package br.com.api.service;

import br.com.api.entity.Cpu;
import br.com.api.repository.CpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CpuService {

    @Autowired
    private CpuRepository cpuRepository;

    public Cpu save (Cpu cpu) {
        Date dateNow = new Date();
        cpu.setPurchase_date(cpu.getPurchase_date());
        cpu.setArrival_date(dateNow);
        if(cpu.isOverclock()){
            System.out.println("This processor has been overclocked");
        } else{
            System.out.println("This processor has not been overclocked");

        }

        return this.cpuRepository.save(cpu);
    }

    public List<Cpu> listAll() {
        return this.cpuRepository.findAll();
    }

    public void delete(Long cpuId) throws Exception {
        Optional<Cpu> c = this.cpuRepository.findById(cpuId);
        if (c.isPresent()) {
            this.cpuRepository.delete(c.get());
        } else {
            throw new Exception(("ERRO AO DELETAR CPU" + cpuId));
        }
    }
    public Cpu update(Cpu cpu) throws Exception{
    Optional<Cpu> editCpu = this.cpuRepository.findById(cpu.getId());
            if(editCpu.isPresent()){
                Cpu c = editCpu.get();
            c.setBrand(cpu.getBrand());
            c.setArrival_date(cpu.getArrival_date());
            c.setClock(cpu.getClock());
            c.setCores(cpu.getCores());
            c.setModel(cpu.getModel());
            c.setPurchase_date(cpu.getPurchase_date());
            c.setPurchase_price(cpu.getPurchase_price());
            c.setSale_value(cpu.getSale_value());
            c.setThreads(cpu.getThreads());
            return c;
            } else {
                throw new Exception("ERRO AO ATUALIZAR"+cpu.getId());
            }

    }




}