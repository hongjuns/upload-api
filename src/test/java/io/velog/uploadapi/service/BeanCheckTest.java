package io.velog.uploadapi.service;
import io.velog.uploadapi.UploadApiApplication;
import io.velog.uploadapi.property.FileStorageProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.multipart.MultipartFile;


@SpringBootTest
public class BeanCheckTest {

    @Autowired
    private FileStorageService fileStorageService;

    private final FileService fileService;

    @Autowired
    public BeanCheckTest(FileService fileS3ServiceImpl) {
        this.fileService = fileS3ServiceImpl;
    }

    @Test
    void basicScan(){
    }
}
