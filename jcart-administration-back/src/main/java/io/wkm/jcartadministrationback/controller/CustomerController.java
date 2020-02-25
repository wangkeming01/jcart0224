package io.wkm.jcartadministrationback.controller;

import io.wkm.jcartadministrationback.dto.in.CustomerListInDTO;
import io.wkm.jcartadministrationback.dto.out.CustomerListOutDTO;
import io.wkm.jcartadministrationback.dto.out.CustomerShowOutDTO;
import io.wkm.jcartadministrationback.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping("/search")
    public PageOutDTO<CustomerListOutDTO> search(@RequestBody CustomerListInDTO customerListInDTO,@RequestParam Integer pageNum){
        return null;
    }
    @GetMapping("/getById")
    public CustomerShowOutDTO getById(@RequestParam Integer customerId){
        return null;
    }

    @PostMapping("/disable")
    public void disable(@RequestParam Integer customerId){

    }

}
