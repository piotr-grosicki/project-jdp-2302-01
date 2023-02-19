package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {

    private Integer id;
    private LocalDateTime orderTime;
    private BigDecimal totalPrice;
    private boolean status;
    private Integer userId;
    private Integer cartId;
}
