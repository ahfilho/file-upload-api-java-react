package br.com.api.storage;


import br.com.api.FilePath;
import br.com.api.entity.Img;
import br.com.api.entity.Ssd;
import br.com.api.interfaces.FileLinkCreatorSsd;
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
@RequestMapping("/url/ssd")
public class BuildFileLinkSsd implements FileLinkCreatorSsd {

    private final FileRepository fileRepository;

    public BuildFileLinkSsd(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/files/{id}")
    public Ssd linkFile(Ssd ssd) {

        List<File> files = List.of(new File(String.valueOf(FilePath.rootSsd)).listFiles());
        List<Img> listImg = fileRepository.findAll();

        List<String> ssdListaNomeImagemDoBanco = listImg.stream().map(Img::getFileName).collect(Collectors.toList());
        List<String> listaNomeImagemDiretorio = files.stream().map(File::getName).collect(Collectors.toList());

        List<String> imagemsEmComum = ssdListaNomeImagemDoBanco.stream().filter(listaNomeImagemDiretorio::contains).collect(Collectors.toList());

        List<String> linkParaImagemEmComum = new ArrayList<>();

        String download = null;
        for (String nomeDaImagem : imagemsEmComum
        ) {
            Img img2 = listImg.stream().filter(i -> i.getFileName().equals(nomeDaImagem)).findFirst().orElse(null);

            if (img2 != null) {
                download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(String.valueOf(ssd.getId()))
                        .toUriString();
                ssd.setImg(img2);
                ssd.setId(ssd.getId());
                linkParaImagemEmComum.add(download);
            }
        }
        ssd.setUrl(download);

        return ssd;

    }
}
