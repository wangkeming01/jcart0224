package io.wkm.jcartadministrationback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {
    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile image){
        return null;
    }
}
