package br.com.api.service;

import br.com.api.entity.Category;
import br.com.api.entity.Cpu;
import br.com.api.entity.Image;
import br.com.api.repository.CategoryRepository;
import br.com.api.repository.CpuRepository;
import br.com.api.repository.OfferImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CpuService {

    private final Path root = Paths.get("uploads");

    @Autowired
    private CpuRepository cpuRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OfferImageRepository offerImageRepository;

    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("ERRO AO INICIALIZAR O DIRETÓRIO");
        }
    }

    public void save(Cpu cpu, MultipartFile file, Category category) throws IOException {

        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        Image img = new Image();
        Date dateNow = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        cpu.setPurchaseDate(dateNow);
        cpu.setArrivalDate(dateNow);

        img.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        img.setContentType(file.getContentType());
        img.setData(file.getBytes());
        img.setSize(file.getSize());

        if (cpu.isOverclock()) {
            System.out.println("This processor has been overclocked");
        } else {
            System.out.println("This processor has not been overclocked");

        }

        this.cpuRepository.save(cpu);
        this.categoryRepository.save(category);
        this.offerImageRepository.save(img);

    }

    public List<Cpu> listAll() {
        return this.cpuRepository.findAll();
    }

    public void delete(Long cpu) throws Exception {
        Optional<Cpu> c = this.cpuRepository.findById(cpu);
        if (c.isPresent()) {
            this.cpuRepository.delete(c.get());
        } else {
            throw new Exception(("ERRO AO DELETAR CPU" + cpu));
        }
    }

    public Cpu update(Cpu cpu) throws Exception {
        Optional<Cpu> editCpu = this.cpuRepository.findById(cpu.getId());
        if (editCpu.isPresent()) {
            Cpu c = editCpu.get();
            c.setBrand(cpu.getBrand());
            c.setArrivalDate(cpu.getArrivalDate());
            c.setClock(cpu.getClock());
            c.setCores(cpu.getCores());
            c.setModel(cpu.getModel());
            c.setPurchaseDate(cpu.getPurchaseDate());
            c.setPurchasePrice(cpu.getPurchasePrice());
            c.setSaleValue(cpu.getSaleValue());
            c.setThreads(cpu.getThreads());
            return c;
        } else {
            throw new Exception("ERRO AO ATUALIZAR" + cpu.getId());
        }

    }


}