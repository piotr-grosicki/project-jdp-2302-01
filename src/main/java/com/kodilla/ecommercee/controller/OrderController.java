package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping
    public List<SpringDataJaxb.OrderDto> getOrders(){
        return new ArrayList<>();
    }

    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto){

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
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderDto;

    }

    @DeleteMapping(value = "{/orderId}")
    public void deleteOrder(@PathVariable Long orderId){

    }
}
