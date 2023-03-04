package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private String nickName;
    private Boolean status;
    private List<Cart> cartList;
    private List<Order> orderList;
}
