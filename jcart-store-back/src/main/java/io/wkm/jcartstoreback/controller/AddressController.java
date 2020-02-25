package io.wkm.jcartstoreback.controller;

import io.wkm.jcartstoreback.dto.in.AddressCreateInDTO;
import io.wkm.jcartstoreback.dto.in.AddressUpdateInDTO;
import io.wkm.jcartstoreback.dto.out.AddressListOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @GetMapping("/getListByCustomerId")
    public List<AddressListOutDTO> getListByCustomerId(@RequestAttribute Integer customerId){
        return  null;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody AddressCreateInDTO addressCreateInDTO){
        return null;
    }

    @PostMapping("/update")
    public void update(@RequestBody AddressUpdateInDTO addressUpdateInDTO,@RequestAttribute Integer addressId){

    }
}
