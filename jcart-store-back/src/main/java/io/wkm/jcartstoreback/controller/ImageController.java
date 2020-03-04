package io.wkm.jcartstoreback.controller;

import io.wkm.jcartstoreback.constant.ClientExceptionConstant;
import io.wkm.jcartstoreback.exception.ClientException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {
    private List<String> imageExts = Arrays.asList("jpg","png","png");
    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile image) throws ClientException, Exception {
        String originalFilename = image.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String ext = split[split.length - 1];
        ext = ext.toLowerCase();
        boolean contains = imageExts.contains(ext);
        if (!contains){
            throw new ClientException(ClientExceptionConstant.IMAGE_INVALID_ERRCODE, ClientExceptionConstant.IMAGE_INVALID_ERRMSG);
        }
        String uuid = UUID.randomUUID().toString();
        String filename = String.format("%s.%s", uuid, ext);
        String filepath = String.format("www/image/%s", filename);
        try(FileOutputStream out = new FileOutputStream(filepath)){
            byte[] data = image.getBytes();
            out.write(data);
        }
        return filename;
    }
}
