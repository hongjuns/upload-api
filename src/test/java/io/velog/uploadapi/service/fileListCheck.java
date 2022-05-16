package io.velog.uploadapi.service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class fileListCheck {

    private Path fileStorageLocation;

    @Test
    void checkFileList (){
        fileStorageLocation = Paths.get("upload-path/User/upload")
                .toAbsolutePath()
                .normalize();

        File folder = new File(fileStorageLocation.toString());
        File files[] = folder.listFiles();

        for(File fileList: files) {
            System.out.println("fileList" + fileList);
        }
    }
}
