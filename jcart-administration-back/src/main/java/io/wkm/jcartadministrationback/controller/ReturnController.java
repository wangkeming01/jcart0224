package io.wkm.jcartadministrationback.controller;

import io.wkm.jcartadministrationback.dto.in.ReturnSearchInDTO;
import io.wkm.jcartadministrationback.dto.in.ReturnUpdateActionInDTO;
import io.wkm.jcartadministrationback.dto.out.PageOutDTO;
import io.wkm.jcartadministrationback.dto.out.ReturnListOutDTO;
import io.wkm.jcartadministrationback.dto.out.ReturnShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class ReturnController {

    @GetMapping("/search")
    public PageOutDTO<ReturnListOutDTO> search(@RequestBody ReturnSearchInDTO returnSearchInDTO, @RequestParam Integer pageNum){
        return null;
    }

    @GetMapping("/getById")
    public ReturnShowOutDTO getById(@RequestParam Integer returnId){
        return null;
    }

    @PostMapping("/updateAction")
    public void updateAction(@RequestBody ReturnUpdateActionInDTO returnUpdateActionInDTO){

    }

}
