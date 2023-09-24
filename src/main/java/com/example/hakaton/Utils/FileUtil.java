package com.example.hakaton.Utils;

import com.example.hakaton.exception.UtilException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    private static final Path root = Paths.get("./images");
    public static String storePhoto(MultipartFile photo, String uuid) {
        String photoPath ="./images/";

        try {
            File path = new File(photoPath + uuid + photo.getOriginalFilename());
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(photo.getBytes());
            output.close();
            return uuid + photo.getOriginalFilename();
        } catch (Exception e) {
            throw new UtilException("Error while storing a photo to directory: " + e.getMessage());
        }
    }

    public static Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
