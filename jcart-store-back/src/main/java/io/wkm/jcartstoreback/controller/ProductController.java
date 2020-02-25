package io.wkm.jcartstoreback.controller;

import io.wkm.jcartstoreback.dto.in.ProductSearchInDTO;
import io.wkm.jcartstoreback.dto.out.PageOutDTO;
import io.wkm.jcartstoreback.dto.out.ProductSearchOutDTO;
import io.wkm.jcartstoreback.dto.out.ProductShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/search")
    public PageOutDTO<ProductSearchOutDTO> search(@RequestBody ProductSearchInDTO productSearchInDTO, @RequestParam Integer pageNum){
        return null;
    }
    @GetMapping("/getById")
    public ProductShowOutDTO getById(@RequestParam Integer productId){
        return null;
    }
}
