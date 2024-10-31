//package com.sound.audiosounds.logic;
//
//import javax.sound.sampled.*;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class AudioLogicImpl {
//    private static boolean isRecording = true;
//    void logic() throws LineUnavailableException, FileNotFoundException {
//        int sampleRate = 44100;
//        int channels = 1;
//        int frameSize = 2;
//        AudioFormat af = new AudioFormat(sampleRate, 16 , channels, true, true);
//        DataLine.Info info =  new DataLine.Info(TargetDataLine.class, af);
//        TargetDataLine td1 = (TargetDataLine) AudioSystem.getLine(info);
//        td1.open(af);
//        td1.start();
//        byte[] buffer = new byte[4096];
//        int bytesRead;
//        try(FileOutputStream fos = new FileOutputStream("output.mp3")){
//            while(isRecording){
//                bytesRead = td1.read(buffer, 0, buffer.length);
//                if(bytesRead> = 0){
//                    fos.write(buffer, 0, bytesRead);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        td1.stop();t
//    }
//}
