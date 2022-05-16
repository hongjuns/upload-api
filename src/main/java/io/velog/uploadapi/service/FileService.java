package io.velog.uploadapi.service;

import io.velog.uploadapi.payload.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
public interface FileService {
     UploadFileResponse upload(MultipartFile file) throws IOException;

}
