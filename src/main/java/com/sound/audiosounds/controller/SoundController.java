package com.sound.audiosounds.controller;

import com.sound.audiosounds.logic.AudioLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
@CrossOrigin
public class SoundController {

    @Autowired
    private AudioLogic audioLogic;
    int count = 0;

    @PostMapping("/api/Sound")
    public ResponseEntity<String> startSound(@RequestParam("file") MultipartFile multipartFile) throws IOException, ClassNotFoundException, InterruptedException {
        count = count + 1;

        try {

            String fileName = System.currentTimeMillis() + "_" + "#" + count + "_#manju.wav";
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("convert to file");
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.write(multipartFile.getBytes());
                    fileOutputStream.flush();
                } catch (Exception e) {
                }
                audioLogic.soundService(file);
            }
        } catch (Exception e) {
            System.out.println("ERROR ::: " + e);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/upload")
    public ResponseEntity<String> upload() {
        audioLogic.uploadIntoS3();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

