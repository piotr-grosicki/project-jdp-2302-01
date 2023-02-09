package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ORDER")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    private Integer id;

    @Column(name="ORDER TIME")
    private LocalDateTime orderTime;

    @Column(name="TOTAL PRICE")
    private BigDecimal totalPrice;

    @Column(name="STATUS")
    private boolean status;
    private Cart cart;
    private User user;

    public Order(Integer id, BigDecimal totalPrice, boolean status) {
        this.id = id;
        this.orderTime = LocalDateTime.now();
        this.totalPrice = totalPrice;
        this.status = status;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="CART_ID")
    public Cart getCarts(){
        return cart;
    }

    public void setCarts(Cart cart){
        this.cart = cart;
    }

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
