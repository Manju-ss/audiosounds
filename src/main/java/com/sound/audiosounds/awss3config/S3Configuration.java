package com.sound.audiosounds.awss3config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {

    private String region = "ap-south-1";

    @Bean
    public AmazonS3 generateS3Client(){
    AWSCredentials credentials = new BasicAWSCredentials("accesskey", "secrateKey");
    return AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
    }

}
