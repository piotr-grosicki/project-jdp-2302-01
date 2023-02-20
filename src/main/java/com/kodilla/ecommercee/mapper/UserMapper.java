package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getNickName(),
                userDto.getStatus(),
                userDto.getCartList(),
                userDto.getOrderList()
        );
    }

    public UserDto mapToUserDto(User user) {
        List<Cart> cartList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();

        if (!user.getCarts().isEmpty()) {
            cartList = user.getCarts();
        }

        if (!user.getOrders().isEmpty()) {
            orderList = user.getOrders();
        }

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getNickName(),
                user.getIsBlocked(),
                cartList,
                orderList
        );
    }


}
