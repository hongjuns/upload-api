package io.velog.uploadapi;

import io.velog.uploadapi.property.FileStorageProperties;
import io.velog.uploadapi.service.FileStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class UploadApiApplicationTests {
    @Autowired
    private FileStorageService fileStorageService;
}