package io.wkm.jcartstoreback.service;

import com.github.pagehelper.Page;
import io.wkm.jcartstoreback.dto.in.OrderCheckoutInDTO;
import io.wkm.jcartstoreback.dto.out.OrderListOutDTO;
import io.wkm.jcartstoreback.dto.out.OrderShowOutDTO;
import io.wkm.jcartstoreback.dto.out.PageOutDTO;
import io.wkm.jcartstoreback.pojo.Order;

public interface OrderService {
    Long checkout(OrderCheckoutInDTO orderCheckoutInDTO, Integer customerId);

    Page<Order> getList(Integer pageNum, Integer customerId);

    OrderShowOutDTO getById(Long orderId);
}
