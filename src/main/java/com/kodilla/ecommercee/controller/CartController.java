package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductInCartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/products")
public class CartController {

    @PostMapping
    public void createCart(@RequestBody CartDto cartDto) {
    }

    @GetMapping
    public List<ProductDto> getProductsFromCart() {
        return new ArrayList<>();
    }

    @PutMapping(value = "/{productId}")
    public ProductInCartDto addProduct(@PathVariable Long productId) {
        return new ProductInCartDto(1, 1);
    }

    @DeleteMapping(value = "/{productId}")
    public void deleteProductFromCart(@PathVariable Long productId) {
    }

    @PostMapping(value = "/{cartId}")
    public void createNewOrder(@PathVariable Long cartId) {
    }


}
