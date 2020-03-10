package io.wkm.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.out.OrderListOutDTO;
import io.wkm.jcartadministrationback.dto.out.OrderShowOutDTO;

public interface OrderService {
    Page<OrderListOutDTO> search(Integer pageNum);

    OrderShowOutDTO getById(Long orderId);
}
