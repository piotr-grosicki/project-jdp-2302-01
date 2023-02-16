package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    public Cart mapToCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setStatus(cartDto.isStatus());
        return cart;
    }

    public CartDto mapToCartDto(Cart cart) {
        List<String> products = cart.getProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        int orderId = 0;

        if (cart.getOrder() != null) {
            orderId = cart.getOrder().getId();
        }
        return new CartDto(
                cart.getId(),
                cart.getStatus(),
                products,
                cart.getUser().getId(),
                orderId
        );
    }
}
