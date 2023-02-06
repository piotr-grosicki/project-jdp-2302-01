package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartsDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductInCartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class CartController {

    @PostMapping
    public void createCart(@RequestBody CartsDto cartsDto) {
        //zrobione utworzenie pustego koszyka
    }

    @GetMapping
    public List<ProductDto> getProductsFromCart() {
        return new ArrayList<>();
    }

    @PutMapping(value = "/{cartId}")
    public ProductInCartDto updateProduct(@PathVariable Long cartId) {
        return new ProductInCartDto(1, 1);
    }

    @DeleteMapping(value = "/{cartId}")
    public void deleteProductFromCart(@PathVariable Long cartId) {
    }

    @PostMapping(value = "/{cartId}")
    public void createNewOrder(@PathVariable Long cartId) {
    }


}
