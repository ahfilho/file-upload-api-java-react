package br.com.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import br.com.api.entity.Ssd;
import br.com.api.entity.CpuCategory;
import br.com.api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.ImgSsd;

@Transactional
@Service
public class SsdFileService {

	private final Path rootSsd = Paths.get("uploads/ssd");
	private final CpuCategoryRepository cpuCategoryRepository;
	private final SsdFileRepository ssdFileRepository;
	private final SsdRepository ssdRepository;


	public SsdFileService(CpuCategoryRepository cpuCategoryRepository, SsdFileRepository ssdFileRepository, SsdRepository ssdRepository) {
		this.cpuCategoryRepository = cpuCategoryRepository;
		this.ssdFileRepository = ssdFileRepository;
		this.ssdRepository = ssdRepository;
	}

	public void init() {
		try {
			Files.createDirectory(rootSsd);
		} catch (IOException e) {
			throw new RuntimeException("erro ao inicializar o diretório");
		}
	}

	public void saveFile(MultipartFile file, Ssd ssd, CpuCategory cpuCategoryModel)
			throws IOException {
		Files.copy(file.getInputStream(), this.rootSsd.resolve(file.getOriginalFilename()));
		ImgSsd img = new ImgSsd();

		img.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		img.setContentType(file.getContentType());
		img.setData(file.getBytes());
		img.setFileSize(file.getSize());

		this.ssdFileRepository.save(img);
		this.ssdRepository.save(ssd);
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

	public Optional<ImgSsd> getFile(Long id) {
		return ssdFileRepository.findById(id);
	}
	// LISTA COMPLETA
	public List<ImgSsd> getAllFiles() {
		return ssdFileRepository.findAll();
	}

	public List<ImgSsd> getSql() {
		return ssdFileRepository.consulta_personalizada();
	}

	public List<ImgSsd> terca() {
		return ssdFileRepository.terca();
	}

	public void imgDelete(Long id) throws Exception {
		Optional<ImgSsd> im = this.ssdFileRepository.findById(id);
		if (im.isPresent()) {
			this.ssdFileRepository.delete(im.get());
		} else {
			throw new Exception("ERRO AO DELETAR IMAGEM" + id);
		}

	}

	public void imgDeleteDiretory(Long id) {
		ImgSsd img = new ImgSsd();
		Optional<ImgSsd> opt = this.ssdFileRepository.findById(id);
		if (opt.isPresent()) {

			try {
				long l1 = img.getId();
				boolean teste = FileSystemUtils.deleteRecursively(rootSsd.resolve(Long.toString(l1)));
				System.out.println("Sucesso ao deletar imagem do path.s");
			} catch (Exception e) {
				System.out.println("Erro ao deletar arquivo de imagem do path");
				e.printStackTrace();
			}
		}
	}

	// VERIFICAR AQUI DEPOIS
	public ImgSsd updateImg(MultipartFile file, Ssd ssd) throws IOException {
		Files.copy(file.getInputStream(), this.rootSsd.resolve(file.getOriginalFilename()));
		ImgSsd otherFiles = new ImgSsd();

		otherFiles.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		otherFiles.setContentType(file.getContentType());
		otherFiles.setData(file.getBytes());
		otherFiles.setFileSize(file.getSize());

		return otherFiles;
	}

}
