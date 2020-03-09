package io.wkm.jcartstoreback.mapper;

import io.wkm.jcartstoreback.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(@Param("orderId") Long orderId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKeyWithBLOBs(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}