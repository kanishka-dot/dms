package rpc.git.dms.service;

import org.springframework.web.multipart.MultipartFile;
import rpc.git.dms.model.File;

import java.util.List;
import java.util.Optional;

public interface StorageService {

    String saveAttachment(MultipartFile file) throws Exception;
    void saveFiles(MultipartFile[] files) throws Exception;
    List<File> getAllFiles();

    File getFile(String fileId);
}
