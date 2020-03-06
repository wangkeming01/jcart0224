package io.wkm.jcartstoreback.controller;

import io.wkm.jcartstoreback.dto.in.OrderCheckoutInDTO;
import io.wkm.jcartstoreback.dto.out.OrderListOutDTO;
import io.wkm.jcartstoreback.dto.out.OrderShowOutDTO;
import io.wkm.jcartstoreback.dto.out.PageOutDTO;
import io.wkm.jcartstoreback.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/checkout")
    public Long checkout(@RequestBody OrderCheckoutInDTO orderCheckoutInDTO,@RequestAttribute Integer customerId){
        return orderService.checkout(orderCheckoutInDTO, customerId);
    }

    @GetMapping("/getList")
    public PageOutDTO<OrderListOutDTO> getList(@RequestAttribute Integer customerId){
        return null;
    }

    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        return null;
    }
}
