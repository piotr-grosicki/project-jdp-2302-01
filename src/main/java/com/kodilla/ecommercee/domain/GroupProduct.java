package com.kodilla.ecommercee.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroupProduct {

    @Id
    private Long id;
    private String name;
}
