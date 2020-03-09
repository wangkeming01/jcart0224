package io.wkm.jcartstoreback.service.impl;

import io.wkm.jcartstoreback.mapper.OrderHistoryMapper;
import io.wkm.jcartstoreback.pojo.OrderHistory;
import io.wkm.jcartstoreback.service.OrderHistoryService;
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
}
