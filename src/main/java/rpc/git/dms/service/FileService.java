package rpc.git.dms.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rpc.git.dms.exception.FileNotFoundException;
import rpc.git.dms.model.File;
import rpc.git.dms.repositary.FileRepo;


import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class FileService implements StorageService{

    private  final FileRepo fileRepo;
    @Override
    public String saveAttachment(MultipartFile file) throws Exception {
        java.util.Date now = new java.util.Date();
        java.sql.Date date = new java.sql.Date(now.getTime());
        System.out.println(file.getBytes().length);
        fileRepo.save(File.builder().fileName(file.getOriginalFilename())
                .fileSize(file.getSize())
                .fileExt(file.getContentType())
                .modDate(date)
                .createDate(date)
                .version("1")
                .data(file.getBytes()).build()) ;
        return "Successfully Save";
    }

    @Override
    public void saveFiles(MultipartFile[] files) throws Exception {
    }

    @Override
    public List<File> getAllFiles() {
        return fileRepo.findAll();
    }

    @Override
    public File getFile(String fileId) {
        Optional<File> file = fileRepo.findById(fileId);;
        if(file.isPresent()){
            File newFile = file.get();
            return newFile;
        }
        throw new FileNotFoundException(fileId);
    }
}
