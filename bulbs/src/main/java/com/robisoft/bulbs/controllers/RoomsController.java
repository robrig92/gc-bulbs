package com.robisoft.bulbs.controllers;

import com.robisoft.bulbs.models.Room;
import com.robisoft.bulbs.services.RoomService;
import com.robisoft.bulbs.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class RoomsController {
    private static final String filename = "blueprint.txt";

    @Autowired
    private StorageService storageService;

    @Autowired
    RoomService roomService;

    @CrossOrigin(origins = "*")
    @GetMapping("/api/rooms/{mode}")
    public ResponseEntity<?> calculateRoomsDistribution(@PathVariable String mode) {
        File roomFile = storageService.retrieve(filename);

        if (roomFile == null) {
            return new ResponseEntity<String>("Not file found", HttpStatus.NOT_FOUND);
        }

        Room room = roomService.parse(roomFile);

        if (mode.equals("plain")) {
            return new ResponseEntity<Room>(room, HttpStatus.OK);
        }

        Room solved = roomService.solve(room);
        return new ResponseEntity<Room>(solved, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @PostMapping("/api/rooms")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (!storageService.store(file, filename)) {
            return new ResponseEntity<String>("Error uploading the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("File upload successfully", HttpStatus.OK);
    }
}
