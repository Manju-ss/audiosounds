package com.sound.audiosounds.logic;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

@Service
public class AudioLogic {
    Logger logger = Logger.getLogger("AudioLogic");
    AtomicBoolean atomicBoolean = new AtomicBoolean();
    private final Map<String, File> map = new ConcurrentHashMap<>();
    // Set the target format for the recording
    static final AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
    static TargetDataLine line;
    @Autowired
    private AmazonS3 s3Client;

    private static String bucketName = "soundbucketfiles";


    public void soundService(File file) {        // File where the recorded audio will be saved
        Thread recordThread = new Thread(() -> {
            try {
                // Start capturing audio
                uploadFiles(file);
                logger.info("Updated bro......" + file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        recordThread.start();
    }

    public void uploadFiles(File wav) throws InterruptedException {
//        String fileName = System.currentTimeMillis() + "_" + wav;
        if (atomicBoolean.get()) {
            Thread.sleep(10000);
        }
        map.put(wav.getName(), wav);
//        s3Client.putObject(new PutObjectRequest(bucketName, fileName, wav));
        System.out.println("Uploaded");
    }

    public void uploadIntoS3() {

        atomicBoolean.set(true);
    if(map.isEmpty()){
        System.out.println("EMPTY");
    }
        for (ConcurrentHashMap.Entry<String, File> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey()
                    + ", Value = "
                    + entry.getValue().getAbsolutePath());
            s3Client.putObject(new PutObjectRequest(bucketName, entry.getKey(), entry.getValue()));
            if (entry.getValue().exists() && entry.getValue().delete()) {
                System.out.println("DELETED the File");
                map.remove(entry.getKey());

            } else {
                System.out.println("UNABLE TO DELETE");
            }
        }
        atomicBoolean.set(false);
    }

}
