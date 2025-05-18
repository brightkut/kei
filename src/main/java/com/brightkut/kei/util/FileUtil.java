package com.brightkut.kei.util;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import static java.lang.String.format;

@UtilityClass
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static String saveFile(String fileName, String directoryPath, MultipartFile multipartFile)
            throws IOException {
        Path uploadPath = Paths.get(directoryPath);

        if (!Files.exists(uploadPath)) {
            log.info("Create directory for upload path: {}", uploadPath);
            Files.createDirectories(uploadPath);
        }

        Path filePath;
        try (InputStream inputStream = multipartFile.getInputStream()) {
            filePath = uploadPath.resolve( fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ioe) {
            log.error("Could not save file {} with exception {}", fileName, ioe.getMessage());
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        return filePath.toAbsolutePath().toString();
    }

    public static InputStreamResource getFile(String path) throws FileNotFoundException {
        Path filePath = Paths.get(path); // Update this path with the actual file path

        // Check if a file exists
        if (!Files.exists(filePath)) {
            log.error("Error occur when file not found with file path: {}", filePath);
            throw new RuntimeException(format("Error occur when file not found with file path: %s", filePath));
        }

        // Reading the file
        File file = filePath.toFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        return new InputStreamResource(fileInputStream);
    }

    public static String getFileBase64(String path) throws IOException {
        // Convert a path to file a system path
        Path filePath = Paths.get(path);

        // Check if the file exists
        if (!Files.exists(filePath)) {
            log.error("Error occurred: File not found with file path: {}", filePath);
            throw new RuntimeException(format("Error occurred: File not found with file path: %s", filePath));
        }

        // Read file content into a byte array
        byte[] fileContent = Files.readAllBytes(filePath);

        // Convert a byte array to Base64 encoded string
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
