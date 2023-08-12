package br.com.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.Category;
import br.com.api.entity.Img;
import br.com.api.entity.Ssd;
import br.com.api.repository.FileRepository;

@Transactional
@Service
public class FileService {

	private final Path root = Paths.get("uploadFile");

	@Autowired
	private FileRepository ofertasRepository;

	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("erro ao inicializar o diretório");
		}
	}

	public void saveFile(MultipartFile file, Ssd productModel, Category categoryModel)
			throws IOException {
		Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		Img img = new Img();

		img.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		img.setContentType(file.getContentType());
		img.setData(file.getBytes());
		img.setFileSize(file.getSize());

		this.ofertasRepository.save(img);
		this.ofertasRepository.save(productModel);
		this.ofertasRepository.save(categoryModel);
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
	public void saveCategoria(Category cpm) {
	}

	public void saveProduto(Ssd pm) {

	}

	public Optional<Img> getFile(Long id) {
		return ofertasRepository.findById(id);
	}
	// LISTA COMPLETA
	public List<Img> getAllFiles() {
		return ofertasRepository.findAll();
	}

	public List<Img> getSql() {
		return ofertasRepository.consulta_personalizada();
	}

	public List<Img> terca() {
		return ofertasRepository.terca();
	}

	public void imgDelete(Long id) throws Exception {
		Optional<Img> im = this.ofertasRepository.findById(id);
		if (im.isPresent()) {
			this.ofertasRepository.delete(im.get());
		} else {
			throw new Exception("ERRO AO DELETAR IMAGEM" + id);
		}

	}

	public void imgDeleteDiretory(Long id) {
		Img img = new Img();
		Optional<Img> opt = this.ofertasRepository.findById(id);
		if (opt.isPresent()) {

			try {
				long l1 = img.getId();
				boolean teste = FileSystemUtils.deleteRecursively(root.resolve(Long.toString(l1)));
				System.out.println("Sucesso ao deletar imagem do path.s");
			} catch (Exception e) {
				System.out.println("Erro ao deletar arquivo de imagem do path");
				e.printStackTrace();
			}
		}
	}

	// VERIFICAR AQUI DEPOIS
	public Img updateImg(MultipartFile file, Ssd ssd) throws IOException {
		Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		Img otherFiles = new Img();

		otherFiles.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		otherFiles.setContentType(file.getContentType());
		otherFiles.setData(file.getBytes());
		otherFiles.setFileSize(file.getSize());

		return otherFiles;
	}

}
