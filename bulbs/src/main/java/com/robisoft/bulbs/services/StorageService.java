package com.robisoft.bulbs.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface StorageService {
    public boolean store(MultipartFile file, String filename);
    public File retrieve(String filename);
}
