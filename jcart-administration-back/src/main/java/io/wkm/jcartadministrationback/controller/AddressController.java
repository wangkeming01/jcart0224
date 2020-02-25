package io.wkm.jcartadministrationback.controller;

import io.wkm.jcartadministrationback.dto.out.AddressListOutDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @GetMapping("/getListByCustomer")
    public List<AddressListOutDTO> getListByCustomer(@RequestParam Integer customerId){
        return null;
    }
}
