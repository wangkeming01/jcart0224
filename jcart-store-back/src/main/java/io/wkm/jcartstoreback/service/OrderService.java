package io.wkm.jcartstoreback.service;

import io.wkm.jcartstoreback.dto.in.OrderCheckoutInDTO;

public interface OrderService {
    Long checkout(OrderCheckoutInDTO orderCheckoutInDTO, Integer customerId);
}
