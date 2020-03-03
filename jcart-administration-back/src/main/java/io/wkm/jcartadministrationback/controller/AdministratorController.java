package io.wkm.jcartadministrationback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.pagehelper.Page;
import com.sun.org.apache.bcel.internal.util.BCELifier;
import io.wkm.jcartadministrationback.constant.ClientExceptionConstant;
import io.wkm.jcartadministrationback.dto.in.*;
import io.wkm.jcartadministrationback.dto.out.*;
import io.wkm.jcartadministrationback.enumeration.AdministratorStatus;
import io.wkm.jcartadministrationback.exception.ClientException;
import io.wkm.jcartadministrationback.pojo.Administrator;
import io.wkm.jcartadministrationback.service.AdministratorService;
import io.wkm.jcartadministrationback.util.JWTUtil;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import javax.annotation.Resource;
import java.util.Date;
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
    public AdministratorGetProfileOutDTO getProfile(@RequestAttribute Integer  administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorGetProfileOutDTO administratorGetProfileOutDTO = new AdministratorGetProfileOutDTO();
        administratorGetProfileOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorGetProfileOutDTO.setUsername(administrator.getUsername());
        administratorGetProfileOutDTO.setRealName(administrator.getRealName());
        administratorGetProfileOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorGetProfileOutDTO.setEmail(administrator.getEmail());
        administratorGetProfileOutDTO.setCreateTimeStamp(administrator.getCreateTime().getTime());

        return administratorGetProfileOutDTO;
    }

    @GetMapping("/updateProfile")
    public void updateProfile(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO,@RequestAttribute Integer administratorId){
        Administrator administrator = new Administrator();
        administrator.setRealName(administratorUpdateInDTO.getRealName());
        administrator.setAvatarUrl(administratorUpdateInDTO.getAvatarUrl());
        administrator.setEmail(administratorUpdateInDTO.getEmail());
        administrator.setAdministratorId(administratorId);
        administratorService.update(administrator);
    }

    @PostMapping("/changePwd")
    public void changePwd(@RequestBody AdministratorChangeInDTO administratorChangeInDTO,@RequestAttribute Integer administratorId){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorId);
        administrator.setEncryptedPassword(administratorChangeInDTO.getNewPwd());
        administratorService.changePwd(administrator);
    }

    @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email){
        return null;
    }

    @GetMapping("/resetPwd")
    public void resetPwd(@RequestBody AdministratorResetPwdInDTO administratorResetPwdInDTO){

    }

    @GetMapping("/getList")
    public PageOutDTO<AdministratorListOutDTO> getList(@RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<AdministratorListOutDTO> page = administratorService.getList(pageNum);
        PageOutDTO<AdministratorListOutDTO> administratorListOutDTOPageOutDTO = new PageOutDTO<>();
        administratorListOutDTOPageOutDTO.setList(page);
        administratorListOutDTOPageOutDTO.setPageSize(page.getPageSize());
        administratorListOutDTOPageOutDTO.setPageNum(page.getPageNum());
        administratorListOutDTOPageOutDTO.setTotal(page.getTotal());
        return administratorListOutDTOPageOutDTO;
    }

    @GetMapping("/getById")
    public AdministratorShowOutDTO getById(@RequestAttribute Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorShowOutDTO administratorShowOutDTO = new AdministratorShowOutDTO();
        administratorShowOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorShowOutDTO.setUsername(administrator.getUsername());
        administratorShowOutDTO.setRealName(administrator.getRealName());
        administratorShowOutDTO.setEmail(administrator.getEmail());
        administratorShowOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorShowOutDTO.setStatus(administrator.getStatus());
        return administratorShowOutDTO;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
        Administrator administrator = new Administrator();
        administrator.setEmail(administratorCreateInDTO.getEmail());
        administrator.setAvatarUrl(administratorCreateInDTO.getAvatarUrl());
        administrator.setRealName(administratorCreateInDTO.getRealName());
        administrator.setUsername(administratorCreateInDTO.getUsername());
        administrator.setCreateTime(new Date());
        administrator.setStatus((byte) AdministratorStatus.Enable.ordinal());

        String s = BCrypt.withDefaults().hashToString(12, administratorCreateInDTO.getPassword().toCharArray());
        administrator.setEncryptedPassword(s);
        administratorService.create(administrator);
        return administrator.getAdministratorId();
    }

    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorUpdateInDTO.getAdministratorId());
        administrator.setRealName(administratorUpdateInDTO.getRealName());
        administrator.setEmail(administratorUpdateInDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateInDTO.getAvatarUrl());
        administrator.setStatus(administratorUpdateInDTO.getStatus());
        String password = administratorUpdateInDTO.getPassword();
        if (password != null && !password.isEmpty() ){
            String s = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            administrator.setEncryptedPassword(password);
        }
        administratorService.update(administrator);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer administratorId){
        administratorService.delete(administratorId);
    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> administratorIds){
        administratorService.batchDelete(administratorIds);
    }

}
