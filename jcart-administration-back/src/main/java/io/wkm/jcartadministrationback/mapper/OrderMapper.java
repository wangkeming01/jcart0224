package io.wkm.jcartadministrationback.mapper;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.out.OrderListOutDTO;
import io.wkm.jcartadministrationback.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Page<OrderListOutDTO> search();
}