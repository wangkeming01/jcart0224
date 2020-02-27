package io.wkm.jcartadministrationback.service.impl;

import com.alibaba.fastjson.JSON;
import io.wkm.jcartadministrationback.dto.in.ProductCreateInDTO;
import io.wkm.jcartadministrationback.dto.in.ProductSearchInDTO;
import io.wkm.jcartadministrationback.dto.in.ProductUpdateInDTO;
import io.wkm.jcartadministrationback.dto.out.PageOutDTO;
import io.wkm.jcartadministrationback.dto.out.ProductListOutDTO;
import io.wkm.jcartadministrationback.dto.out.ProductShowOutDTO;
import io.wkm.jcartadministrationback.mapper.ProductDetailMapper;
import io.wkm.jcartadministrationback.mapper.ProductMapper;
import io.wkm.jcartadministrationback.pojo.Product;
import io.wkm.jcartadministrationback.pojo.ProductDetail;
import io.wkm.jcartadministrationback.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductDetailMapper productDetailMapper;

    @Override
    @Transactional
    public Integer create(ProductCreateInDTO productCreateInDTO) {
        Product product = new Product();
        product.setProductCode(productCreateInDTO.getProductCode());
        product.setProductName(productCreateInDTO.getProductName());
        product.setDiscount(productCreateInDTO.getDiscount());
        product.setMainPicUrl(productCreateInDTO.getMainPicUrl());
        product.setPrice(productCreateInDTO.getPrice());
        product.setProductAbstract(productCreateInDTO.getDescription().substring(0,Math.min(100,productCreateInDTO.getDescription().length())));
        product.setStatus(productCreateInDTO.getStatus());
        product.setRewordPoints(productCreateInDTO.getRewordPoints());
        product.setSortOrder(productCreateInDTO.getSortOrder());
        product.setStockQuantity(productCreateInDTO.getStockQuantity());
        productMapper.insertSelective(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription(productCreateInDTO.getDescription());
        productDetail.setOtherPicUrls(JSON.toJSONString(productCreateInDTO.getOtherPicUrls()));
        productDetail.setProductId(product.getProductId());

        productDetailMapper.insertSelective(productDetail);
        return product.getProductId();
    }

    @Override
    @Transactional
    public void update(ProductUpdateInDTO productUpdateInDTO) {
        Product product = new Product();
        product.setProductId(productUpdateInDTO.getProductId());
        product.setProductName(productUpdateInDTO.getProductName());
        product.setPrice(productUpdateInDTO.getPrice());
        product.setDiscount(productUpdateInDTO.getDiscount());
        product.setStockQuantity(productUpdateInDTO.getStockQuantity());
        product.setStatus(productUpdateInDTO.getStatus());
        product.setMainPicUrl(productUpdateInDTO.getMainPicUrl());
        product.setRewordPoints(productUpdateInDTO.getRewordPoints());
        product.setSortOrder(productUpdateInDTO.getSortOrder());
        product.setProductAbstract(productUpdateInDTO.getDescription().substring(0,Math.min(100,productUpdateInDTO.getDescription().length())));
        productMapper.updateByPrimaryKeySelective(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(productUpdateInDTO.getProductId());
        productDetail.setOtherPicUrls(JSON.toJSONString(productUpdateInDTO.getOtherPicUrls()));
        productDetail.setDescription(productUpdateInDTO.getDescription());
        productDetailMapper.updateByPrimaryKeySelective(productDetail);
    }

    @Override
    public ProductShowOutDTO getById(Integer productId) {

        return null;
    }

    @Override
    public PageOutDTO<ProductListOutDTO> search(Integer pageNum, ProductSearchInDTO productSearchInDTO) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Integer productId) {
        productMapper.deleteByPrimaryKey(productId);
        productDetailMapper.deleteByPrimaryKey(productId);
    }

    @Override
    @Transactional
    public void batchDelete(List<Integer> productIds) {
        productMapper.batchDelete(productIds);
        productDetailMapper.batchDelete(productIds);
    }
}
