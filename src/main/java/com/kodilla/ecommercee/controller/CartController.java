package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductInCartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductsFromCart() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping()
    public ResponseEntity<ProductInCartDto> addProduct(@RequestBody ProductInCartDto productInCartDto) {
        return ResponseEntity.ok(new ProductInCartDto(1,1));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProductFromCart(@RequestBody ProductInCartDto productInCartDto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{cartId}")
    public ResponseEntity<Void> createNewOrder(@PathVariable Long cartId) {
        return ResponseEntity.ok().build();
    }


}
