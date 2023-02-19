package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderMapper {

    public Order mapToOrder (OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setStatus(orderDto.isStatus());
        order.setOrderTime(orderDto.getOrderTime());
        return order;
    }

    public OrderDto mapToOrderDto (Order order){
        int cartId = 0;

        if (order.getCart() != null) {
            cartId = order.getCart().getId();
        }

        return new OrderDto(
                order.getId(),
                order.getOrderTime(),
                order.getTotalPrice(),
                order.isStatus(),
                order.getUser().getId(),
                cartId
        );
    }

    public List<Order> mapToOrderList(List<OrderDto> orderDtoList){
        return orderDtoList.stream()
                .map(this::mapToOrder)
                .collect(Collectors.toList());
    }

    public List<OrderDto> mapToOrderDtoList (List<Order> orderList){
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
