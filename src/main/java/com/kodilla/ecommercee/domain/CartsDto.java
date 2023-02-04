package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartsDto {
    private int id;
    private int userId;
    private boolean status;
}
