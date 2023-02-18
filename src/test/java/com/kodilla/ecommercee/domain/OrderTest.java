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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        //Then
        int orderId = order.getId();
        Optional<Order> readOrder = orderRepository.findById(orderId);
        assertEquals("John", readOrder.get().getUser().getName());
        assertEquals(1, cartRepository.count());

        //CleanUp
        orderRepository.deleteById(orderId);
    }

    @Test
    public void testFindAll(){
        //Given
        Order order1 = new Order();
        order1.setOrderTime(LocalDateTime.now());
        order1.setStatus(true);
        Order order2 = new Order();
        order2.setOrderTime(LocalDateTime.now());
        order2.setStatus(false);
        Order order3 = new Order();
        order3.setOrderTime(LocalDateTime.now());
        order3.setStatus(true);

        //When
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        //Then
        int order1Id = order1.getId();
        int order2Id = order2.getId();
        int order3Id = order3.getId();

        assertEquals(3, orderRepository.count());
        assertEquals(3, orderRepository.findAll().size());

        //CleanUp
        orderRepository.deleteById(order1Id);
        orderRepository.deleteById(order2Id);
        orderRepository.deleteById(order3Id);
    }

    @Test
    public void testFindById(){
        //Given
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(true);

        //When
        orderRepository.save(order);

        //Then
        int orderId = order.getId();

        assertEquals(true, orderRepository.findById(orderId).get().isStatus());

        //CleanUp
        orderRepository.deleteById(orderId);
    }
}