package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartRepository repository;

    public Cart saveCart(Cart cart) {
        return repository.save(cart);
    }

    public List<Product> getProducts(Integer cartId) throws CartNotFoundException {
        Cart cart = repository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return cart.getProducts();
    }

    public Cart getCart(Integer cartId) throws CartNotFoundException {
        return repository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }
}
