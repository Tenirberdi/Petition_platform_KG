package com.example.hakaton.Utils;

import com.example.hakaton.entity.FileEntity;
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
import java.util.UUID;

public class FileUtil {
    private static final Path root = Paths.get("./images");
    public static final String folder = "/images/";
    public static String storePhoto(MultipartFile photo) {
        String uuid= UUID.randomUUID().toString();

        try {
            File path = new File("." + folder + uuid + photo.getOriginalFilename());
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(photo.getBytes());
            output.close();
            return folder + uuid + photo.getOriginalFilename();
        } catch (Exception e) {
            throw new UtilException("Error while storing a photo to directory: " + e.getMessage());
        }

    }

    public static void updatePhoto(MultipartFile photo, com.example.hakaton.model.File file) {
        if(photo != null && photo.getSize() != 0 &&
                photo.getOriginalFilename() != null &&
                !photo.getOriginalFilename().equals(file.getFileName())) {
            if(deletePhoto(file)) {
                String path = storePhoto(photo);
                file.setFileName(photo.getOriginalFilename());
                file.setLocationPath(path);
            } else {
                throw new UtilException("Error while storing a photo to directory: could not delete previous photo");
            }
        }
    }

    public static boolean deletePhoto(com.example.hakaton.model.File file) {
        File photo = new File(file.getLocationPath());
        if (photo.exists() && !photo.isDirectory()) {
            return photo.delete();
        }

        return false;
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
