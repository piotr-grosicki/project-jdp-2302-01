package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProduct(Integer id) throws ProductNotFoundException {
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public void deleteProduct (Integer id) throws ProductNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ProductNotFoundException();
        }
    }

}
