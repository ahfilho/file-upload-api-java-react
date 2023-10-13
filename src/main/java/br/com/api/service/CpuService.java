package br.com.api.service;

import br.com.api.controller.ProductController;
import br.com.api.entity.Cpu;
import br.com.api.entity.ImgCpu;
import br.com.api.entity.ProductCategoryCpu;
import br.com.api.entity.ProductCategorySsd;
import br.com.api.enume.CategoryEnum;
import br.com.api.repository.CpuFileRepository;
import br.com.api.repository.CpuRepository;
import br.com.api.repository.ProductCategoryRepositoryCpu;
import br.com.api.repository.ProductCategoryRepositorySsd;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
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
public class CpuService extends ProductController<Cpu> {

    private final Path rootCpu = Paths.get("uploads/cpu");
    private final CpuRepository cpuRepository;
    private final CpuFileRepository cpuFileRepository;

    private final ProductCategoryRepositoryCpu productCategoryCpuRepository;

    public CpuService(CpuRepository cpuRepository, CpuFileRepository cpuFileRepository, ProductCategoryRepositorySsd productCategoryRepositorySsd1, ProductCategoryRepositoryCpu productCategoryCpuRepository) {
        this.cpuRepository = cpuRepository;
        this.cpuFileRepository = cpuFileRepository;
        this.productCategoryCpuRepository = productCategoryCpuRepository;
    }

    public void init() {
        try {
            Files.createDirectory(rootCpu);
        } catch (IOException e) {
            throw new RuntimeException("ERRO AO INICIALIZAR O DIRETÓRIO");
        }
    }

    public void save(Cpu cpu, MultipartFile file, ProductCategoryCpu productCategoryCpu) throws IOException {

        Files.copy(file.getInputStream(), this.rootCpu.resolve(file.getOriginalFilename()));
        ImgCpu imgCpu = new ImgCpu();
        Date dateNow = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        productCategoryCpu.setProductCategory(String.valueOf(CategoryEnum.CPU));

        imgCpu.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgCpu.setContentType(file.getContentType());
        imgCpu.setData(file.getBytes());
        imgCpu.setFileSize(file.getSize());

        cpu.setImgCpu(imgCpu);

        this.cpuRepository.save(cpu);
        productCategoryCpuRepository.save(productCategoryCpu);


    }

    public List<Cpu> listAll() throws Exception {
        return cpuRepository.findAll();
    }

    public void delete(Long cpu) throws Exception {
        Optional<Cpu> c = this.cpuRepository.findById(cpu);
        if (c.isPresent()) {
            Cpu cpu1 = c.get();
            deleteFile(cpu1.getImgCpu().getFileName());
            cpuRepository.delete(cpu1);
            cpuFileRepository.delete(cpu1.getImgCpu());
        } else {
            throw new Exception(("ERRO AO DELETAR CPU" + cpu));
        }
    }

    private void deleteFile(String fileName) {
        List<ImgCpu> imgList = cpuFileRepository.deleteByName(fileName);

        for (ImgCpu img : imgList) {
            cpuFileRepository.delete(img);
            String imgFileName = img.getFileName();
            Path physicalFilePath = rootCpu.resolve(imgFileName);
            File physicalFile = physicalFilePath.toFile();
            if (physicalFile.exists()) {
                File fileCpuImg = new File("uploads");
                physicalFile.delete();
            }
//            File f = new File(String.valueOf(root));
//            if (f.isDirectory()) {
//                File[] fi = f.listFiles();
//
//                // Excluindo arquivos no diretório correspondentes aos objetos Img
//                for (File ff : fi) {
//                    for (Img im : imgList) {
//                        if (ff.getName().equals(im.getFileName())) {
//                            ff.delete();
//                        }
//                    }
//                }
//            }
        }

    }

    public Cpu update(MultipartFile file, ProductCategorySsd productCategorySsd, Cpu cpu) throws Exception {
        Optional<Cpu> editCpu = this.cpuRepository.findById(cpu.getId());
        if (editCpu.isPresent()) {
            Cpu c = editCpu.get();
            c.setBrand(cpu.getBrand());
            c.setArrivalDate(cpu.getArrivalDate());
            c.setClockCount(cpu.getClockCount());
            c.setCoreCount(cpu.getCoreCount());
            c.setModel(cpu.getModel());
            c.setPurchaseDate(cpu.getPurchaseDate());
            c.setPurchasePrice(cpu.getPurchasePrice());
            c.setSaleValue(cpu.getSaleValue());
            c.setThreadCount(cpu.getThreadCount());
            return c;
        } else {
            throw new Exception("ERRO AO ATUALIZAR" + cpu.getId());
        }

    }


    public Cpu searchId(Long id) throws Exception {
        Optional<Cpu> resultSearchCpuId = cpuRepository.findById(id);
        if (resultSearchCpuId.isPresent()) {
            return resultSearchCpuId.get();

        } else {
            throw new Exception("Id inexistente ou erro na busca.");
        }
    }

}