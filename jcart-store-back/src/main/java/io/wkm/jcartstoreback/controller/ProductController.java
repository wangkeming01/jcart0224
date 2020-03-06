package io.wkm.jcartstoreback.controller;

import com.github.pagehelper.Page;
import io.wkm.jcartstoreback.dto.in.ProductSearchInDTO;
import io.wkm.jcartstoreback.dto.out.PageOutDTO;
import io.wkm.jcartstoreback.dto.out.ProductSearchOutDTO;
import io.wkm.jcartstoreback.dto.out.ProductShowOutDTO;
import io.wkm.jcartstoreback.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Resource
    private ProductService productService;
    @GetMapping("/search")
    public PageOutDTO<ProductSearchOutDTO> search( ProductSearchInDTO productSearchInDTO, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<ProductSearchOutDTO> page = productService.search(pageNum);
        PageOutDTO<ProductSearchOutDTO> productSearchOutDTOPageOutDTO = new PageOutDTO<>();
        productSearchOutDTOPageOutDTO.setList(page);
        productSearchOutDTOPageOutDTO.setPageNum(page.getPageNum());
        productSearchOutDTOPageOutDTO.setPageSize(page.getPageSize());
        productSearchOutDTOPageOutDTO.setTotal(page.getTotal());
        return productSearchOutDTOPageOutDTO;
    }
    @GetMapping("/getById")
    public ProductShowOutDTO getById(@RequestParam Integer productId){
        return productService.getById(productId);
    }
}
