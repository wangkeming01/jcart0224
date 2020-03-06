package io.wkm.jcartstoreback.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wkm.jcartstoreback.dto.in.ProductSearchInDTO;
import io.wkm.jcartstoreback.dto.out.ProductSearchOutDTO;
import io.wkm.jcartstoreback.dto.out.ProductShowOutDTO;
import io.wkm.jcartstoreback.mapper.ProductDetailMapper;
import io.wkm.jcartstoreback.mapper.ProductMapper;
import io.wkm.jcartstoreback.pojo.Product;
import io.wkm.jcartstoreback.pojo.ProductDetail;
import io.wkm.jcartstoreback.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductDetailMapper productDetailMapper;

    @Override
    public Page<ProductSearchOutDTO> search(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        return  productMapper.search();
    }

    @Override
    public ProductShowOutDTO getById(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        ProductDetail productDetail = productDetailMapper.selectByPrimaryKey(productId);

        ProductShowOutDTO productShowOutDTO = new ProductShowOutDTO();
        productShowOutDTO.setProductId(product.getProductId());
        productShowOutDTO.setProductCode(product.getProductCode());
        productShowOutDTO.setProductName(product.getProductName());
        productShowOutDTO.setPrice(product.getPrice());
        productShowOutDTO.setDiscount(product.getDiscount());
        productShowOutDTO.setMainPicUrl(product.getMainPicUrl());
        productShowOutDTO.setRewordPoints(product.getRewordPoints());
        productShowOutDTO.setStockQuantity(product.getStockQuantity());

        productShowOutDTO.setDescription(productDetail.getDescription());
        String otherPicUrls = productDetail.getOtherPicUrls();

        List<String> other = JSON.parseArray(otherPicUrls, String.class);
        productShowOutDTO.setOtherPicUrls(other);
        return productShowOutDTO;
    }
}
