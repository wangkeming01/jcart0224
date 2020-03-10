package io.wkm.jcartadministrationback.service;

import io.wkm.jcartadministrationback.pojo.OrderHistory;

import java.util.List;

public interface OrderHistoryService {
    List<OrderHistory> getByOrderId(Long orderId);

    void create(OrderHistory orderHistory);
}
