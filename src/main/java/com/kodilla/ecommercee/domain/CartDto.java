package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {
    private int id;
    private boolean status;
    private List<String> products;
    private int userId;
    private int orderId;

    public CartDto(boolean status) {
        this.status = status;
    }
}
