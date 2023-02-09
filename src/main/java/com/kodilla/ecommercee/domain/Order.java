package com.kodilla.ecommercee.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "ORDERS")
public class Order {

    @Id
    private Long id;
    private LocalDateTime orderTime;
    private BigDecimal totalPrice;
    private boolean status;
}
