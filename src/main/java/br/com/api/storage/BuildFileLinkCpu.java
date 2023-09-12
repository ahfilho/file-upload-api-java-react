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
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/url/cpu")
public class BuildFileLinkCpu implements FileLinkCreatorCpu {

    private  final FileRepository fileRepository;

    public BuildFileLinkCpu(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/files/{id}")
    public Cpu linkFile(Cpu cpu) {

        FileRepository fileRepository;
        File[] files = new File(String.valueOf(FilePath.rootCpu)).listFiles();
        System.out.println(Arrays.toString(files));

        Img img = new Img();
        System.out.println();

        for (File f : files
        ) {

                System.out.println(f.getName());



            String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(String.valueOf(cpu.getId()))
                    .toUriString();
            cpu.setId(cpu.getId());
            cpu.setUrl(download);
            System.out.println(download);

        }
        return cpu;

    }
}
