package br.com.api.service;

import br.com.api.entity.CpuCategory;
import br.com.api.entity.Cpu;
import br.com.api.entity.ImgCpu;
import br.com.api.entity.ImgSsd;
import br.com.api.repository.CpuCategoryRepository;
import br.com.api.repository.CpuFileRepository;
import br.com.api.repository.CpuRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CpuFileService {

    private final Path rootCpu = Paths.get("uploads/cpu");

    private final CpuCategoryRepository cpuCategoryRepository;
    private final CpuFileRepository cpuFileRepository;

    private final CpuRepository cpuRepository;
    public CpuFileService(CpuCategoryRepository cpuCategoryRepository, CpuFileRepository cpuFileRepository, CpuRepository cpuRepository) {
        this.cpuCategoryRepository = cpuCategoryRepository;
        this.cpuFileRepository = cpuFileRepository;
        this.cpuRepository = cpuRepository;
    }

    public void init() {
        try {
            Files.createDirectory(rootCpu);
        } catch (IOException e) {
            throw new RuntimeException("erro ao inicializar o diretório");
        }
    }

    public void saveFile(MultipartFile file, Cpu cpu, CpuCategory cpuCategoryModel)
            throws IOException {
        Files.copy(file.getInputStream(), this.rootCpu.resolve(file.getOriginalFilename()));
        ImgCpu img = new ImgCpu();

        img.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        img.setContentType(file.getContentType());
        img.setData(file.getBytes());
        img.setFileSize(file.getSize());

        this.cpuFileRepository.save(img);
        this.cpuRepository.save(cpu);
        this.cpuCategoryRepository.save(cpuCategoryModel);
    }

    /*
     * Envia mais de um arquivo por requisição; Vai ficar aqui para caso precise
     * modificar algo.
     *
     * public void saveFile(MultipartFile[] file, ProdutoModel product_name,
     * CategoriaProdutoModel product_category) throws IOException { for
     * (MultipartFile multipartFile : file) {
     * Files.copy(multipartFile.getInputStream(),
     * this.root.resolve(multipartFile.getOriginalFilename()));
     *
     * ImgProdutoModel ofertas = new ImgProdutoModel();
     * ofertas.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
     * ofertas.setContentType(multipartFile.getContentType());
     * ofertas.setData(multipartFile.getBytes());
     * ofertas.setSize(multipartFile.getSize());
     *
     * this.ofertasRepository.save(ofertas);
     * this.ofertasRepository.save(product_name);
     * this.ofertasRepository.save(product_category); } }
     */
    public void saveCategoria(CpuCategory cpm) {
    }

    public void saveProduto(Cpu pm) {

    }

    public Optional<ImgCpu> getFile(Long id) {
        return cpuFileRepository.findById(id);
    }

    // LISTA COMPLETA
    public List<ImgCpu> getAllFiles() {
        return cpuFileRepository.findAll();
    }

    public List<ImgCpu> getSql() {
        return cpuFileRepository.consulta_personalizada();
    }

    public List<ImgCpu> terca() {
        return cpuFileRepository.terca();
    }

    public void imgDelete(Long id) throws Exception {
        Optional<ImgCpu> im = this.cpuFileRepository.findById(id);
        if (im.isPresent()) {
            this.cpuFileRepository.delete(im.get());
        } else {
            throw new Exception("ERRO AO DELETAR IMAGEM" + id);
        }

    }

    public void imgDeleteDiretory(Long id) {
        ImgSsd img = new ImgSsd();
        Optional<ImgCpu> opt = this.cpuFileRepository.findById(id);
        if (opt.isPresent()) {

            try {
                long l1 = img.getId();
                boolean teste = FileSystemUtils.deleteRecursively(rootCpu.resolve(Long.toString(l1)));
                System.out.println("Sucesso ao deletar imagem do path.s");
            } catch (Exception e) {
                System.out.println("Erro ao deletar arquivo de imagem do path");
                e.printStackTrace();
            }
        }
    }

    // VERIFICAR AQUI DEPOIS
    public ImgSsd updateImg(MultipartFile file, Cpu cpu) throws IOException {
        Files.copy(file.getInputStream(), this.rootCpu.resolve(file.getOriginalFilename()));
        ImgSsd otherFiles = new ImgSsd();

        otherFiles.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        otherFiles.setContentType(file.getContentType());
        otherFiles.setData(file.getBytes());
        otherFiles.setFileSize(file.getSize());

        return otherFiles;
    }
}
