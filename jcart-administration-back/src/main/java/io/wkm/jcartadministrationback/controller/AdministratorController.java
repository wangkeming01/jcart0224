package io.wkm.jcartadministrationback.controller;

import io.wkm.jcartadministrationback.dto.in.AdministratorCreateInDTO;
import io.wkm.jcartadministrationback.dto.in.AdministratorLoginInDTO;
import io.wkm.jcartadministrationback.dto.in.AdministratorResetPwdInDTO;
import io.wkm.jcartadministrationback.dto.in.AdministratorUpdateInDTO;
import io.wkm.jcartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import io.wkm.jcartadministrationback.dto.out.AdministratorListOutDTO;
import io.wkm.jcartadministrationback.dto.out.AdministratorShowOutDTO;
import io.wkm.jcartadministrationback.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @GetMapping("/login")
    public String login(@RequestBody AdministratorLoginInDTO administratorLoginInDTO){
        return null;
    }

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(@RequestParam Integer  administratorId){
        return null;
    }

    @GetMapping("/updateProfile")
    public void updateProfile(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){
    }

    @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email){
        return null;
    }

    @GetMapping("/resetPwd")
    public void resetPwd(@RequestBody AdministratorResetPwdInDTO administratorResetPwdInDTO){

    }

    @GetMapping("/getList")
    public PageOutDTO<AdministratorListOutDTO> getList(){
        return null;
    }

    @GetMapping("/getById")
    public AdministratorShowOutDTO getById(@RequestParam Integer administratorId){
        return null;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
        return null;
    }

    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){

    }

    @PostMapping("/delete")
    public void delete(@RequestParam Integer administratorId){

    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestParam List<Integer> administratorIds){

    }

}
