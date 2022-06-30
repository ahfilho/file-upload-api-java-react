package br.com.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.entity.CategoryProduct;
import br.com.api.entity.ImgProdutoModel;
import br.com.api.entity.Product;
import br.com.api.entity.OfferImageResponse;
import br.com.api.service.OfferImageService;

@RestController
@RequestMapping("/files")
public class OfferImageController {

	@Autowired
	private OfferImageService ofertasService;

	/*
	 * Envia mais de um arquivo por requisição; vai ficar aqui caso precise.
	 * 
	 * @PostMapping public ResponseEntity<String>
	 * uploadImgOferta(@RequestParam("file") MultipartFile[] file, ProdutoModel pm,
	 * CategoriaProdutoModel cpm) {
	 * 
	 * try { ofertasService.saveFile(file, pm, cpm);
	 * 
	 * return ResponseEntity.status(HttpStatus.OK)
	 * .body(String.format("sucesso no upload")); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.OK)
	 * .body(String.format("falha no upload %s"));
	 * 
	 * } }
	 */

	// Envia apenas um arquivo por requisição

	// SALVA UMA IMAGEM, UM PRODUTO E UMA CATEGORIA
	@PostMapping
	public ResponseEntity<String> uploadImgOferta(@RequestParam("file") MultipartFile file, Product productModel,
			CategoryProduct categoryProductModel) {

		try {
			ofertasService.saveFile(file, productModel, categoryProductModel);

			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("sucesso no upload", file.getOriginalFilename()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("falha no upload %s", file.getOriginalFilename()));

		}
	}

	@GetMapping("terca")
	// @Scheduled(fixedRate = 2000)
	public List<OfferImageResponse> teste3() {
		return ofertasService.terca().stream().map(this::mapToQuery).collect(Collectors.toList());
	}

	private OfferImageResponse mapTo_Terca_Feira(ImgProdutoModel img) {
		long l1 = img.getId();
		String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(Long.toString(l1)).toUriString();
		OfferImageResponse iqr = new OfferImageResponse();
		iqr.setId(img.getId());
		iqr.setName(img.getName());
		iqr.setContentType(img.getContentType());
		// iqr.setSize(img.getSize());
		iqr.setUrl(downloadURL);
		return iqr;
	}

	// Ainda para terminar, só falta fazer o select do repository. Mas já funciona
	// certinho.
	@GetMapping("teste")
	// @Scheduled(fixedRate = 2000) // Vou usar isso para exibir as determinadas
	// consultas de acordo com o dia da semana.
	public List<OfferImageResponse> teste2() {
		return ofertasService.getSql().stream().map(this::mapToQuery).collect(Collectors.toList());
	}

	// FUNCIONANDO: traz as imagens de acordo com o select do repository
	private OfferImageResponse mapToQuery(ImgProdutoModel img) {

		long l1 = img.getId();
		String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(Long.toString(l1)).toUriString();
		OfferImageResponse iqr = new OfferImageResponse();
		iqr.setId(img.getId());
		iqr.setName(img.getName());
		iqr.setContentType(img.getContentType());
		// iqr.setSize(img.getSize());
		iqr.setUrl(downloadURL);
		return iqr;
	}

	@GetMapping
	public List<OfferImageResponse> list() {
		return ofertasService.getAllFiles().stream().map(this::mapToFileResponse).collect(Collectors.toList());
	}

	private OfferImageResponse mapToFileResponse(ImgProdutoModel ofertasModel) {

		long l2 = ofertasModel.getId();
		String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(Long.toString(l2)).toUriString();

		OfferImageResponse ofertas = new OfferImageResponse();
		ofertas.setId(ofertasModel.getId());
		ofertas.setName(ofertasModel.getName());
		ofertas.setContentType(ofertasModel.getContentType());
		ofertas.setSize(ofertasModel.getSize());
		ofertas.setUrl(downloadURL);

		return ofertas;
	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		Optional<ImgProdutoModel> file = ofertasService.getFile(id);
		if (!file.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ImgProdutoModel ofertasmodel = file.get();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "teste de upload/ filename=" + ofertasmodel.getName() + "\"")
				.contentType(MediaType.valueOf(ofertasmodel.getContentType())).body(ofertasmodel.getData());

	}

	@DeleteMapping("/{id}")
	public HttpStatus deleta(@PathVariable Long id) throws Exception {
		ofertasService.imgDeleteDiretory(id);
		this.ofertasService.imgDelete(id);
		return HttpStatus.OK;
	}

	// VERIFICAR AQUI DEPOIS
	@PutMapping("/{id}")
	public ResponseEntity<String> uploadImgOferta(@RequestParam("file") MultipartFile file) {

		try {
			ofertasService.updateImg(file, null);

			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("sucesso no upload", file.getOriginalFilename()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("falha no upload %s", file.getOriginalFilename()));

		}
	}
}