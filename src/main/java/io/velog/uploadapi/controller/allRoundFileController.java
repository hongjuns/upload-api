package io.velog.uploadapi.controller;

import io.velog.uploadapi.payload.UploadFileResponse;
import io.velog.uploadapi.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class allRoundFileController {

    private static final Logger logger = LoggerFactory.getLogger(allRoundFileController.class);
    private final FileService fileService;

    /*
    * fileLocalServiceImpl : Local Upload Component
    * fileS3ServiceImpl : S3 Upload Component
    */
    public allRoundFileController(FileService fileS3ServiceImpl) {
        this.fileService = fileS3ServiceImpl;
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        UploadFileResponse uploadFileResponse = null;
        try{
            uploadFileResponse = fileService.upload(file);
        }catch (IOException ex){
            logger.info("uploadFile IOException : " + ex);
            uploadFileResponse = new UploadFileResponse ("","","",0);
        }
        return uploadFileResponse;
    }

    @GetMapping(path = "/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam String fileName) {

        try{
            byte[] data = fileService.download(fileName);
            ByteArrayResource resource = new ByteArrayResource(data);
            return ResponseEntity
                    .ok()
                    .contentLength(data.length)
                    .header("Content-type", "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"")
                    .body(resource);
        }catch (IOException ex){
            logger.info("downloadFile IOException : " + ex);
            return ResponseEntity.badRequest().contentLength(0).body(null);
        }

    }

}
