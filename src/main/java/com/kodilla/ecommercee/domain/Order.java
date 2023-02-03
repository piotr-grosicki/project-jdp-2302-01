package com.kodilla.ecommercee.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private Long id;
    private LocalDateTime orderTime;
    private BigDecimal totalPrice;
    private boolean status;
}
