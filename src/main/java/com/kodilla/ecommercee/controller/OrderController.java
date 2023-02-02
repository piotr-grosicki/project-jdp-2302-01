package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders(){
        return new ArrayList<>();
    }

    @PostMapping
    public void createOrder(OrderDto orderDto){

    }

    @GetMapping(value = "/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return new OrderDto(
                1L,
                LocalDateTime.of(2022,12,15,18,36),
                new BigDecimal(456.45),
                true);
    }

    @PutMapping
    public OrderDto updateOrder(OrderDto orderDto) {
        return new OrderDto(
                1L,
                LocalDateTime.of(2023, 1, 15, 15, 46),
                new BigDecimal(995.45),
                false);
    }

    @DeleteMapping(value = "{/orderId}")
    public void deleteOrder(@PathVariable Long orderId){

    }
}