package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name="ID", unique=true)
    private Integer id;

    @Column(name="ORDER_TIME")
    private LocalDateTime orderTime;

    @Column(name="TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name="STATUS")
    private boolean status;

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="CART_ID")
    private Cart cart;
}
