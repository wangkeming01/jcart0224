package io.wkm.jcartadministrationback.service.impl;

import io.wkm.jcartadministrationback.mapper.OrderHistoryMapper;
import io.wkm.jcartadministrationback.pojo.OrderHistory;
import io.wkm.jcartadministrationback.service.OrderHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
    @Resource
    private OrderHistoryMapper orderHistoryMapper;
    @Override
    public List<OrderHistory> getByOrderId(Long orderId) {
        return orderHistoryMapper.getByOrderId(orderId);
    }

    @Override
    public void create(OrderHistory orderHistory) {
        orderHistoryMapper.insertSelective(orderHistory);
    }
}
