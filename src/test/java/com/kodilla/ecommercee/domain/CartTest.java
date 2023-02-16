package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest extends TestCase {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveEmptyCart() {
        Cart cart = new Cart();
        cart.setStatus(false);

        cartRepository.save(cart);
        int cartId = cart.getId();

        assertTrue(cartRepository.existsById(cartId));

        //CleanUp
        try {
            productRepository.deleteById(cartId);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testSaveCartWithUserAndOrder() {
        User user = new User();
        user.setName("Milosz");
        user.setSurname("Trol");

        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setTotalPrice(new BigDecimal("75.55"));
        order.setStatus(true);
        order.setUser(user);

        Cart cart = new Cart();
        cart.setStatus(true);
        cart.setUser(user);

        user.getCarts().add(cart);
        user.getOrders().add(order);
        cart.setOrder(order);
        order.setCart(cart);

        userRepository.save(user);
        cartRepository.save(cart);
        orderRepository.save(order);

        int cartId = cart.getId();
        int orderId = order.getId();
        int userId = user.getId();

        assertTrue(cartRepository.findById(cartId).isPresent());
        assertEquals(user.getName(), cartRepository.findById(cartId).get().getUser().getName());
        assertEquals(new BigDecimal("75.55"), cartRepository.findById(cartId).get().getOrder().getTotalPrice());

        //CleanUp
        try {
            cartRepository.deleteById(cartId);
            userRepository.deleteById(userId);
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testSaveCartWithProducts() {
        Cart cart = new Cart();
        cart.setStatus(true);

        Product product = new Product();
        product.setName("Bread");
        product.setDescription("Fresh bread");
        product.setPrice(new BigDecimal("3"));

        Product product1 = new Product();
        product.setName("Donut");
        product.setDescription("With pudding");
        product.setPrice(new BigDecimal("4"));

        Product product2 = new Product();
        product.setName("Ice-cream");
        product.setDescription("Strawberry");
        product.setPrice(new BigDecimal("5"));

        product.getCarts().add(cart);
        product1.getCarts().add(cart);
        product2.getCarts().add(cart);

        cart.getProducts().add(product);
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);

        cartRepository.save(cart);
        productRepository.save(product);
        productRepository.save(product1);
        productRepository.save(product2);

        int cartId = cart.getId();
        int productId = product.getId();
        int product1Id = product.getId();
        int product2Id = product.getId();

        assertTrue(cartRepository.findById(cartId).isPresent());
        assertEquals(3, cartRepository.findById(cartId).get().getProducts().size());
        assertEquals(product1.getName(), cartRepository.findById(cartId).get().getProducts().get(1).getName());

        //CleanUp
        try {
            cartRepository.deleteById(cartId);
            productRepository.deleteById(productId);
            productRepository.deleteById(product1Id);
            productRepository.deleteById(product2Id);
        } catch (Exception e) {
            //do nothing
        }


    }


}