package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDbService orderDbService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(){
        List<Order> orders = orderDbService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto){
        Order order = orderMapper.mapToOrder(orderDto);
        orderDbService.saveOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderDbService.getOrder(orderId)));
    }

    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderDbService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Integer orderId) throws OrderNotFoundException {
        orderDbService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
