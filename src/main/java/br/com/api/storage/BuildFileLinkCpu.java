package br.com.api.storage;


import br.com.api.FilePath;
import br.com.api.entity.Cpu;
import br.com.api.entity.Img;
import br.com.api.interfaces.FileLinkCreatorCpu;
import br.com.api.repository.FileRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/url/cpu")
public class BuildFileLinkCpu implements FileLinkCreatorCpu {

    private final FileRepository fileRepository;

    public BuildFileLinkCpu(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/files/{id}")
    public Cpu linkFile(Cpu cpu) {

        List<File> files = List.of(new File(String.valueOf(FilePath.rootCpu)).listFiles());
        List<Img> listImg = fileRepository.findAll();

        List<String> listaNomeDeImagemDoBanco = listImg.stream().map(Img::getFileName).collect(Collectors.toList());
        List<String> listaNomeDeImagemDiretorio = files.stream().map(File::getName).collect(Collectors.toList());

        List<String> imagensEmComum = listaNomeDeImagemDoBanco.stream().filter(listaNomeDeImagemDiretorio::contains).collect(Collectors.toList());

        List<String> linkParaImagemEmComum = new ArrayList<>();

        String download = null;
        for (String nomeDaImagem : imagensEmComum
        ) {
            Img img1 = listImg.stream().filter(i -> i.getFileName().equals(nomeDaImagem)).findFirst().orElse(null);

            if (img1 != null) {
                download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(String.valueOf(cpu.getImg().getId()))
                        .toUriString();
                cpu.setImg(img1);
                cpu.setId(cpu.getId());
                cpu.setUrl(download);
                linkParaImagemEmComum.add(download);
            }
        }

        cpu.setUrl(download);

        return cpu;
    }

}
