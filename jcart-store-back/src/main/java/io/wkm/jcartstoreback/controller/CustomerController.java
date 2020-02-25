package io.wkm.jcartstoreback.controller;

import io.wkm.jcartstoreback.dto.in.CustomerLoginInDTO;
import io.wkm.jcartstoreback.dto.in.CustomerRegisterInDTO;
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
}
