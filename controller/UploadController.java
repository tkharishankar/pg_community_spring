package com.pg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api")
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "http://localhost:8080/Documents";

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            return "You successfully uploaded '" + file.getOriginalFilename() + "'";

        } catch (IOException e) {
            return "File upload error, try again...";
        }

    }

}

