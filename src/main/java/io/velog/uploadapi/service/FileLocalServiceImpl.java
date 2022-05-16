package io.velog.uploadapi.service;

import io.velog.uploadapi.exception.FileStorageException;
import io.velog.uploadapi.payload.UploadFileResponse;
import io.velog.uploadapi.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileLocalServiceImpl implements FileService {

    private final Path fileStorageLocation;

    @Autowired
    public FileLocalServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath()
                .normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }

    }

    @Override
    public UploadFileResponse upload(MultipartFile file) {
        UploadFileResponse uploadFileResponse = null;
        String fileName =  storeFile(file);
        try {
            if(fileName.equals("")){
                new Exception("The file name does not exist.");
            }else {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/downloadFile/")
                        .path(fileName)
                        .toUriString();
                uploadFileResponse = new UploadFileResponse(fileName,fileDownloadUri,file.getContentType(),file.getSize());
            }
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
        return uploadFileResponse;
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

}
