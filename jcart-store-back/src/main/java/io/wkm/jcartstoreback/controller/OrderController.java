package io.wkm.jcartstoreback.controller;

import com.github.pagehelper.Page;
import io.wkm.jcartstoreback.dto.in.OrderCheckoutInDTO;
import io.wkm.jcartstoreback.dto.out.OrderListOutDTO;
import io.wkm.jcartstoreback.dto.out.OrderShowOutDTO;
import io.wkm.jcartstoreback.dto.out.PageOutDTO;
import io.wkm.jcartstoreback.pojo.Order;
import io.wkm.jcartstoreback.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/checkout")
    public Long checkout(@RequestBody OrderCheckoutInDTO orderCheckoutInDTO,@RequestAttribute Integer customerId){
        return orderService.checkout(orderCheckoutInDTO, customerId);
    }

    @GetMapping("/getList")
    public PageOutDTO<OrderListOutDTO> getList(@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestAttribute Integer customerId){
        Page<Order> page = orderService.getList(pageNum, customerId);
        List<OrderListOutDTO> orderListOutDTOS = page.stream().map(order -> {
            OrderListOutDTO orderListOutDTO = new OrderListOutDTO();
            orderListOutDTO.setOrderId(order.getOrderId());
            orderListOutDTO.setStatus(order.getStatus());
            orderListOutDTO.setTotalPrice(order.getTotalPrice());
            orderListOutDTO.setCreateTimestamp(order.getCreateTime().getTime());
            return orderListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<OrderListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setList(orderListOutDTOS);

        return pageOutDTO;
    }

    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        return orderService.getById(orderId);
    }
}
