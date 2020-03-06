package io.wkm.jcartstoreback.service.impl;

import io.wkm.jcartstoreback.dto.in.OrderCheckoutInDTO;
import io.wkm.jcartstoreback.dto.in.OrderProductInDTO;
import io.wkm.jcartstoreback.dto.out.ProductShowOutDTO;
import io.wkm.jcartstoreback.mapper.OrderMapper;
import io.wkm.jcartstoreback.mapper.ProductMapper;
import io.wkm.jcartstoreback.pojo.Order;
import io.wkm.jcartstoreback.pojo.Product;
import io.wkm.jcartstoreback.service.OrderService;
import io.wkm.jcartstoreback.service.ProductService;
import io.wkm.jcartstoreback.vo.OrderProductVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ProductMapper productMapper;
    @Override
    public Long checkout(OrderCheckoutInDTO orderCheckoutInDTO, Integer customerId) {
        List<OrderProductInDTO> orderProducts = orderCheckoutInDTO.getOrderProducts();
        List<OrderProductVO> orderProductVOS = orderProducts.stream().map(orderProductInDTO -> {
            Product orderProduct = productMapper.selectByPrimaryKey(orderProductInDTO.getProductId());
            OrderProductVO orderProductVO = new OrderProductVO();
            orderProductVO.setProductId(orderProduct.getProductId());
            orderProductVO.setProductCode(orderProduct.getProductCode());
            orderProductVO.setProductName(orderProduct.getProductName());
            Integer quantity = orderProductInDTO.getQuantity();
            orderProductVO.setQuantity(quantity);
            Double unitPrice = orderProduct.getPrice() * orderProduct.getDiscount();
            orderProductVO.setUnitPrice(unitPrice);
            Double totalPrice = unitPrice * quantity;
            orderProductVO.setTotalPrice(totalPrice);
            Integer unitRewordPoints = orderProduct.getRewordPoints();
            orderProductVO.setUnitRewordPoints(unitRewordPoints);
            Integer totalRewordPoints = unitRewordPoints * quantity;
            orderProductVO.setTotalRewordPoints(totalRewordPoints);
            return orderProductVO;
        }).collect(Collectors.toList());
        double allTotalPrice = orderProductVOS.stream().mapToDouble(p -> p.getTotalPrice()).sum();
        int allTotalRewordPoints = orderProductVOS.stream().mapToInt(p -> p.getTotalRewordPoints()).sum();

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setRewordPoints(allTotalRewordPoints);
        order.setTotalPrice(allTotalPrice);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        return null;
    }
}
