package com.kodilla.ecommercee.domain;

<<<<<<< HEAD
public class OrderDto {
=======
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime orderTime;
    private BigDecimal totalPrice;
    private boolean status;
>>>>>>> f4515c575f9cb1cf6ce5d755c8d2c48ac0742654
}
