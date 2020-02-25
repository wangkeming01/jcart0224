package io.wkm.jcartadministrationback.controller;

import io.wkm.jcartadministrationback.dto.in.OrderSearchInDTO;
import io.wkm.jcartadministrationback.dto.out.OrderListOutDTO;
import io.wkm.jcartadministrationback.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/search")
    public PageOutDTO<OrderListOutDTO> search(@RequestBody OrderSearchInDTO orderSearchInDTO, @RequestParam Integer pageNum){
        return null;
    }
}
