package io.wkm.jcartstoreback.mapper;

import com.github.pagehelper.Page;
import io.wkm.jcartstoreback.dto.out.ProductSearchOutDTO;
import io.wkm.jcartstoreback.pojo.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Page<ProductSearchOutDTO> search();

}