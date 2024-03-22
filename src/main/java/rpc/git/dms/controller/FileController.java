package rpc.git.dms.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rpc.git.dms.exception.FileNotFoundException;
import rpc.git.dms.model.File;
import rpc.git.dms.response.FileResponse;
import rpc.git.dms.service.StorageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dms/file/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FileController {

    private final StorageService fileService;

    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        try {
            return  fileService.saveAttachment(file);
        }catch (Exception e){
            return "Error upload file";
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FileResponse>> getAllFiles(){
        List<File> files = fileService.getAllFiles();
        List<FileResponse> responseList =files.stream().map(file -> {
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentServletMapping()
                    .path("/dms/file/api/v1")
                    .path("/downloads/")
                    .path(file.getRecordId())
                    .toUriString();
            return new FileResponse(file.getFileName(),downloadUrl,file.getFileExt(), file.getFileSize());
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/downloads/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        File file= fileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getData());

    }
}
