package io.velog.uploadapi.service;

import com.amazonaws.services.s3.AmazonS3Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class S3FindObjectTest {

    private String bucket ="dev-portfolio-data";
    private final AmazonS3Client amazonS3Client;


    @Autowired
    public S3FindObjectTest(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Test
    void listBucketsTest(){
        System.out.println("amazonS3Client" + amazonS3Client.listBuckets());
    }
}
