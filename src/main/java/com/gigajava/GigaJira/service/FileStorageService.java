package com.gigajava.GigaJira.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(Long taskId, MultipartFile file) {

        try {
            String folderPath = uploadDir + "/" + taskId;

            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path path = Paths.get(folderPath, fileName);

            Files.write(path, file.getBytes());

            return "/" + folderPath + "/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }
    }
}