package io.wkm.jcartadministrationback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sun.org.apache.bcel.internal.util.BCELifier;
import io.wkm.jcartadministrationback.constant.ClientExceptionConstant;
import io.wkm.jcartadministrationback.dto.in.AdministratorCreateInDTO;
import io.wkm.jcartadministrationback.dto.in.AdministratorLoginInDTO;
import io.wkm.jcartadministrationback.dto.in.AdministratorResetPwdInDTO;
import io.wkm.jcartadministrationback.dto.in.AdministratorUpdateInDTO;
import io.wkm.jcartadministrationback.dto.out.*;
import io.wkm.jcartadministrationback.exception.ClientException;
import io.wkm.jcartadministrationback.pojo.Administrator;
import io.wkm.jcartadministrationback.service.AdministratorService;
import io.wkm.jcartadministrationback.util.JWTUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @Resource
    private AdministratorService administratorService;

    @Resource
    private JWTUtil jwtUtil;
    @GetMapping("/login")
    public AdministratorLoginOutDTO login( AdministratorLoginInDTO administratorLoginInDTO) throws ClientException {
        Administrator administrator = administratorService.getByUserName(administratorLoginInDTO.getUsername());
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE,ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encryptedPassword = administrator.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(administratorLoginInDTO.getPassword().toCharArray(),encryptedPassword);
        if (result.verified) {
            AdministratorLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(administrator);
            return administratorLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }
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
