package com.github.yumengliu.crypto.controller;

import com.github.yumengliu.crypto.model.BtcOrder;
import com.github.yumengliu.crypto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public UUID createOrder(@RequestBody Map<String, String> jsonMap) {
        return orderService.createOrder(UUID.fromString(jsonMap.get("account_id")), Double.parseDouble(jsonMap.get("price_limit")));
    }

    @GetMapping("/order/{orderId}")
    public BtcOrder getOrder(@PathVariable UUID orderId) {
        return orderService.getOrder(orderId);
    }
}
