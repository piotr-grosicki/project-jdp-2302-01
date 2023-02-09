package com.kodilla.ecommercee.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USERS")
public class User {

    @Id
    private Integer id;
}
