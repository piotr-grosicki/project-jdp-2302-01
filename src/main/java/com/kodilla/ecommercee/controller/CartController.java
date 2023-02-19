package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final CartDbService cartDbService;
    private final ProductDbService productDbService;
    private final UserRepository userRepository;
    private final OrderDbService orderDbService;

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        User user = new User();
        cart.setUser(user); //todo
        userRepository.save(user); //todo
        cartDbService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{cartId}")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable Integer cartId) throws CartNotFoundException {
        List<Product> productList = cartDbService.getProducts(cartId);
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productList));
    }

    @PutMapping()
    public ResponseEntity<CartDto> addProduct(@RequestBody ProductInCartDto productInCartDto) throws CartNotFoundException, ProductNotFoundException {
        Product product = productDbService.getProduct(productInCartDto.getProductId());
        Cart cart = cartDbService.getCart(productInCartDto.getCartId());
        cart.getProducts().add(product);
        product.getCarts().add(cart);
        System.out.println(cart.getProducts());
        Cart savedCart = cartDbService.saveCart(cart);
        return ResponseEntity.ok(cartMapper.mapToCartDto(savedCart));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProductFromCart(@RequestBody ProductInCartDto productInCartDto) throws ProductNotFoundException, CartNotFoundException {
        Cart cart = cartDbService.getCart(productInCartDto.getCartId());
        Product product = productDbService.getProduct(productInCartDto.getProductId());
        cart.getProducts().remove(product);
        product.getCarts().remove(cart);
        cartDbService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{cartId}")
    public ResponseEntity<Void> createNewOrder(@PathVariable Integer cartId) throws CartNotFoundException {
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(true);
        order.setUser(cartDbService.getCart(cartId).getUser());
        order.setCart(cartDbService.getCart(cartId));
        orderDbService.saveOrder(order);
        return ResponseEntity.ok().build();
    }
}
