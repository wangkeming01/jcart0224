package io.wkm.jcartstoreback.service;

import io.wkm.jcartstoreback.pojo.OrderHistory;

import java.util.List;

public interface OrderHistoryService {
    List<OrderHistory> getByOrderId(Long orderId);
}
