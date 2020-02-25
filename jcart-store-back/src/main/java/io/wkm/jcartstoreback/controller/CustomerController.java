package io.wkm.jcartstoreback.controller;

import io.wkm.jcartstoreback.dto.in.*;
import io.wkm.jcartstoreback.dto.out.CustomerProfileOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @PostMapping("/register")
    public Integer register(@RequestBody CustomerRegisterInDTO customerRegisterInDTO){
        return  null;
    }

    @PostMapping("/login")
    public String login(@RequestBody CustomerLoginInDTO customerLoginInDTO){
        return null;
    }

    @GetMapping("/getProfile")
    public CustomerProfileOutDTO getProfile(@RequestAttribute Integer customerId){
        return null;
    }

    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody CustomerUpdateProfileInDTO customerUpdateProfileInDTO,@RequestAttribute Integer customerId){

    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody CustomerChangePasswordInDTO customerChangePasswordInDTO,@RequestAttribute Integer customerId){
    }

    @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email){
        return null;
    }

    @PostMapping("/resetPwd")
    public void resetPwd(@RequestBody CustomerResetPwdInDTO customerResetPwdInDTO){

    }
}
