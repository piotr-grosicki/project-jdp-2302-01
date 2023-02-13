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
public class UserTest extends TestCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;


    @Test
    public void testSaveUser(){
        //Given
        User user = new User();
        user.setName("Tomek");
        user.setSurname("Nowak");
        user.setNickName("NT");

        //When
        userRepository.save(user);
        int userId = user.getId();

        //Then
        assertTrue(userRepository.existsById(userId));

        //CleanUp
        try{
            userRepository.deleteById(userId);
        }catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testSaveUserWithOrders(){
        //Given
        User user = new User();
        user.setName("Tomek");
        user.setSurname("Nowak");
        user.setNickName("NT");

        Order order1 = new Order();
        order1.setOrderTime(LocalDateTime.now());
        order1.setStatus(true);
        order1.setTotalPrice(new BigDecimal("345.24"));

        Order order2 = new Order();
        order2.setOrderTime(LocalDateTime.now());
        order1.setStatus(true);
        order1.setTotalPrice(new BigDecimal("34.99"));

        user.getOrders().add(order1);
        user.getOrders().add(order2);

        //When
        userRepository.save(user);
        int userId = user.getId();
        int order1Id = order1.getId();
        int order2Id = order2.getId();

        //Then
        assertTrue(userRepository.existsById(userId));
        assertTrue(orderRepository.existsById(order1Id));
        assertTrue(orderRepository.existsById(order2Id));

        //CleanUp
        try{
            userRepository.deleteById(userId);
            orderRepository.deleteById(order1Id);
            orderRepository.deleteById(order2Id);
        }catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testSaveUserInCards(){
        //Given
        User user = new User();
        user.setName("Tomek");
        user.setSurname("Nowak");
        user.setNickName("NT");

        Cart cart1 = new Cart();
        cart1.setStatus(true);

        Cart cart2 = new Cart();
        cart2.setStatus(true);

        user.getCarts().add(cart1);
        user.getCarts().add(cart2);

        //When
        userRepository.save(user);
        int userId = user.getId();
        int cart1Id = cart1.getId();
        int cart2Id = cart2.getId();

        //Then
        assertTrue(userRepository.existsById(userId));
        assertTrue(cartRepository.existsById(cart1Id));
        assertTrue(cartRepository.existsById(cart2Id));

        //CleanUp
        try{
            userRepository.deleteById(userId);
            cartRepository.deleteById(cart1Id);
            cartRepository.deleteById(cart2Id);
        }catch (Exception e){
            //do nothing
        }
    }
}
