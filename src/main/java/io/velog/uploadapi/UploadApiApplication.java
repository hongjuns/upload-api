package io.velog.uploadapi;

import io.velog.uploadapi.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class UploadApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApiApplication.class, args);
    }

}
