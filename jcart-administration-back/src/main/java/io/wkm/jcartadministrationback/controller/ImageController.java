package io.wkm.jcartadministrationback.controller;

import io.wkm.jcartadministrationback.constant.ClientExceptionConstant;
import io.wkm.jcartadministrationback.exception.ClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {
    private List<String> imageExts = Arrays.asList("jpg","png","png");
    @Value("${www.image.baseUrl}")
    private String baseUrl;
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
        return baseUrl+"/"+filename;
    }
}
