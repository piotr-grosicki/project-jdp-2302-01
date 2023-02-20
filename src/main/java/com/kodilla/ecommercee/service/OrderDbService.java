package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDbService {

    private final OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrder(Integer id) throws OrderNotFoundException {
        return repository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    public void deleteOrder(Integer id) throws OrderNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new OrderNotFoundException();
        }
    }

}
