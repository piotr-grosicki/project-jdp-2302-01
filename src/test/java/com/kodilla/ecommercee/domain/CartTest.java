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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest extends TestCase {
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
            cartRepository.deleteById(cartId);
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


}