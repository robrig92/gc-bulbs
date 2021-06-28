package com.robisoft.bulbs.controllers;

import com.robisoft.bulbs.models.Blueprint;
import com.robisoft.bulbs.services.BlueprintParser;
import com.robisoft.bulbs.services.BlueprintService;
import com.robisoft.bulbs.services.BulbDistributionCalculator;
import com.robisoft.bulbs.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class BlueprintsController {
    private static final String filename = "blueprint.txt";

    @Autowired
    private StorageService storageService;

    @Autowired
    BlueprintService blueprintService;

    @CrossOrigin(origins = "*")
    @GetMapping("/api/blueprints/{mode}")
    public ResponseEntity<?> calculateRoomsDistribution(@PathVariable String mode) {
        File blueprintFile = storageService.retrieve(filename);

        if (blueprintFile == null) {
            return new ResponseEntity<String>("Not file found", HttpStatus.NOT_FOUND);
        }

        Blueprint blueprint = blueprintService.parse(blueprintFile);

        if (mode.equals("plain")) {
            return new ResponseEntity<Blueprint>(blueprint, HttpStatus.OK);
        }

        Blueprint solved = blueprintService.solve(blueprint);
        return new ResponseEntity<Blueprint>(solved, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @PostMapping("/api/blueprints")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (!storageService.store(file, filename)) {
            return new ResponseEntity<String>("Error uploading the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("File upload successfully", HttpStatus.OK);
    }
}
