package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartsDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductInCartDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartController {

    @PostMapping
    public void createCarts(@RequestBody CartsDto cartsDto) {
        //zrobione utworzenie pustego koszyka
    }

    @GetMapping
    public List<ProductDto> getProductsFromCart() {
        return new ArrayList<>();
    }

    @PutMapping
    public ProductInCartDto updateProduct(@RequestBody ProductInCartDto productInCartDto) {
        return new ProductInCartDto(1, 1);
    }

    @DeleteMapping
    public void deleteProductFromCart(@RequestBody ProductInCartDto productInCartDto) {
    }

    @PostMapping
    public void createNewOrder(@RequestBody OrderDto orderDto) {
    }


}
