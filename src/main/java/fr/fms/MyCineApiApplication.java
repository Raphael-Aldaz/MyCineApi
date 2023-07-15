package fr.fms;

import fr.fms.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Slf4j
@SpringBootApplication
public class MyCineApiApplication implements CommandLineRunner {

    @Autowired
    AccountService accountService;


    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Configuration
    public static class StaticResourceConfiguration implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/api/images/**")
                    .addResourceLocations("file:C:/home/user/trainings/images/");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MyCineApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        makeDirectory();
    }
    void makeDirectory() {
        Path targetDirectory = Paths.get("C:\\home\\user\\trainings\\images\\");
        try {
            if (!Files.exists(targetDirectory)) {
                Files.createDirectories(targetDirectory);
                copyImageIfNotExists();
                log.info("Dossier créé");
            } else {
                copyImageIfNotExists();
                log.info("Le dossier existe déjà");
            }
        } catch (Exception e) {
            log.info("Erreur lors de la création du dossier : " + e.getMessage());
        }
    }
    private void copyImageIfNotExists() throws IOException {
        String targetDirectoryPath = "C:/home/user/trainings/images/";
        File targetFile = new File(targetDirectoryPath, "default-image.png");
        if (!targetFile.exists()) {
            String sourceImagePath = "static/picture/default-image.png";
            try (InputStream inputStream = new ClassPathResource(sourceImagePath).getInputStream();
                 OutputStream outputStream = Files.newOutputStream(targetFile.toPath())) {
                FileCopyUtils.copy(inputStream, outputStream);
            }
        }
    }


}
