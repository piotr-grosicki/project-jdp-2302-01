package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer groupProductId;
}
