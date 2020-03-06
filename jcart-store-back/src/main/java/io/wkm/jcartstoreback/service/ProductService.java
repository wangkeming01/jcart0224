package io.wkm.jcartstoreback.service;

import com.github.pagehelper.Page;
import io.wkm.jcartstoreback.dto.in.ProductSearchInDTO;
import io.wkm.jcartstoreback.dto.out.ProductSearchOutDTO;
import io.wkm.jcartstoreback.dto.out.ProductShowOutDTO;

public interface ProductService {

    Page<ProductSearchOutDTO> search(Integer pageNum);

    ProductShowOutDTO getById(Integer productId);
}
