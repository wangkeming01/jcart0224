package io.wkm.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.in.ProductCreateInDTO;
import io.wkm.jcartadministrationback.dto.in.ProductSearchInDTO;
import io.wkm.jcartadministrationback.dto.in.ProductUpdateInDTO;
import io.wkm.jcartadministrationback.dto.out.PageOutDTO;
import io.wkm.jcartadministrationback.dto.out.ProductListOutDTO;
import io.wkm.jcartadministrationback.dto.out.ProductShowOutDTO;

import java.util.List;

public interface ProductService {
    Integer create(ProductCreateInDTO productCreateInDTO);

    void update(ProductUpdateInDTO productUpdateInDTO);

    ProductShowOutDTO getById(Integer productId);


    void delete(Integer productId);

    void batchDelete(List<Integer> productIds);

    Page<ProductListOutDTO> search(Integer pageNum);
}
