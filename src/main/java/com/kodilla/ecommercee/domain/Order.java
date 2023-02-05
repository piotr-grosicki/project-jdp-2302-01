package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.Carts;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor
@Entity
@Table(name = "ORDER")
public class Order {

    private Long id;
    private LocalDateTime orderTime;
    private BigDecimal totalPrice;
    private boolean status;
    private Carts carts;
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
    public Carts getCarts(){
        return carts;
    }

    public void setCarts(Carts carts){
        this.carts = carts;
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
