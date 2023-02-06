package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private Long id;
    private LocalDateTime orderTime;
    private BigDecimal totalPrice;
    private boolean status;
    private Cart cart;
    private User user;

    public Order(Long id, BigDecimal totalPrice, boolean status) {
        this.id = id;
        this.orderTime = LocalDateTime.now();
        this.totalPrice = totalPrice;
        this.status = status;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Column(name="ORDER TIME")
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    private void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    @Column(name="TOTAL PRICE")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name="STATUS")
    public boolean isStatus() {
        return status;
    }

    private void setStatus(boolean status) {
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
