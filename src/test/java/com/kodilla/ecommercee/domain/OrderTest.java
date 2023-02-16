package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest extends TestCase {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveOrder() {
        //Given
        Order order = new Order();
        LocalDateTime time = LocalDateTime.now();
        order.setOrderTime(time);
        BigDecimal price = new BigDecimal("599.30");
        order.setTotalPrice(price);
        order.setStatus(true);

        //When
        orderRepository.save(order);

        //Then
        int orderId = order.getId();
        Optional<Order> readOrder = orderRepository.findById(orderId);
        assertTrue(readOrder.isPresent());
        assertEquals(time, readOrder.get().getOrderTime());
        assertEquals(price, readOrder.get().getTotalPrice());

        //CleanUp
        orderRepository.deleteById(orderId);
    }

    @Test
    public void testSaveOrderWithUserAndCart() {
        //Given
        User user = new User();
        user.setName("John");
        user.setSurname("Smith");

        Cart cart = new Cart();
        cart.setStatus(true);
        cart.setUser(user);

        Order order = new Order();
        LocalDateTime time = LocalDateTime.now();
        order.setOrderTime(time);
        BigDecimal price = new BigDecimal("599.30");
        order.setTotalPrice(price);
        order.setStatus(true);
        order.setUser(user);
        order.setCart(cart);

        //When
        userRepository.save(user);
        orderRepository.save(order);
        cartRepository.save(cart);

        //Then
        int orderId = order.getId();
        Optional<Order> readOrder = orderRepository.findById(orderId);
        assertEquals("John", readOrder.get().getUser().getName());
        assertEquals(1, cartRepository.count());

        //CleanUp
        orderRepository.deleteById(orderId);
    }

}