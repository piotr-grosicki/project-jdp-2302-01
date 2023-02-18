package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GroupProductDto {
    private Integer id;
    private String name;
    private List<String> products;
}
