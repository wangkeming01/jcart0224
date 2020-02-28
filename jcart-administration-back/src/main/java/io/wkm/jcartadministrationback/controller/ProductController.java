package io.wkm.jcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.in.ProductCreateInDTO;
import io.wkm.jcartadministrationback.dto.in.ProductSearchInDTO;
import io.wkm.jcartadministrationback.dto.in.ProductUpdateInDTO;
import io.wkm.jcartadministrationback.dto.out.PageOutDTO;
import io.wkm.jcartadministrationback.dto.out.ProductListOutDTO;
import io.wkm.jcartadministrationback.dto.out.ProductShowOutDTO;
import io.wkm.jcartadministrationback.pojo.Product;
import io.wkm.jcartadministrationback.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Resource
    private ProductService productService;
    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> getList(@RequestParam Integer pageNum,  ProductSearchInDTO productSearchInDTO){
        Page<ProductListOutDTO> page = productService.search(pageNum);
        PageOutDTO<ProductListOutDTO> productListOutDTOPageOutDTO = new PageOutDTO<>();
        productListOutDTOPageOutDTO.setTotal(page.getTotal());
        productListOutDTOPageOutDTO.setPageNum(pageNum);
        productListOutDTOPageOutDTO.setList(page);
        productListOutDTOPageOutDTO.setPageSize(page.getPageSize());
        return productListOutDTOPageOutDTO;
    }

    @GetMapping("/getById")
    public ProductShowOutDTO getById(@RequestParam Integer productId){
        return productService.getById(productId);
    }

    @PostMapping("/create")
    public Integer create(@RequestBody ProductCreateInDTO productCreateInDTO){
        return productService.create(productCreateInDTO);
    }

    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateInDTO productUpdateInDTO){
        productService.update(productUpdateInDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer productId){
        productService.delete(productId);
    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> productIds){
        productService.batchDelete(productIds);
    }
}
