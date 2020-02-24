package io.wkm.jcartadministrationback.controller;

import io.wkm.jcartadministrationback.dto.in.AdministratorLoginInDTO;
import io.wkm.jcartadministrationback.dto.in.AdministratorUpdateInDTO;
import io.wkm.jcartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @GetMapping("/login")
    public String login(AdministratorLoginInDTO administratorLoginInDTO){
        return null;
    }

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(Integer  administratorId){
        return null;
    }

    @GetMapping("/updateProfile")
    public void updateProfile(AdministratorUpdateInDTO administratorUpdateInDTO){
        
    }
}
