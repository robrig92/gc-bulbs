package com.robisoft.bulbs.services.impls;

import com.robisoft.bulbs.services.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService implements StorageService {

    private final Path uploadsDir = Paths.get("uploads");

    @Override
    public boolean store(MultipartFile file, String filename) {
        try {
            if (!containerExists(uploadsDir)) {
                if (!createDir(uploadsDir)) {
                    return false;
                }
            }

            if (fileExistsAlready(uploadsDir.resolve(filename))) {
                deleteFile(uploadsDir.resolve(filename));
                uploadsDir.resolve(filename).toFile().delete();
            }

            Files.copy(file.getInputStream(), uploadsDir.resolve(filename));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean containerExists(Path dir) {
        return dir.toFile().isDirectory();
    }

    private boolean createDir(Path dir) {
        return dir.toFile().mkdir();
    }

    private boolean fileExistsAlready(Path file) {
        return file.toFile().exists();
    }

    private boolean deleteFile(Path file) {
        return file.toFile().delete();
    }

    @Override
    public File retrieve(String filename) {
        File room = new File(uploadsDir.resolve(filename).toString());

        if (!room.exists()) {
            return null;
        }

        return room;
    }
}
