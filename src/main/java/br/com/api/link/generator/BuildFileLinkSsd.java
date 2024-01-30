    package br.com.api.link.generator;


    import br.com.api.entity.Ssd;
    import br.com.api.interfaces.FileLinkCreatorSsd;
    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

    @RestController
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/url/ssd")
    public class BuildFileLinkSsd implements FileLinkCreatorSsd {

        @GetMapping("/files/{id}")
        public Ssd linkForFileSsd(Ssd ssd) {

            String downloadLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/ssd/").path(String.valueOf(ssd.getId()))
                    .toUriString();
            ssd.setId(ssd.getId());
            ssd.setUrl(downloadLink);
            return ssd;

        }
    }