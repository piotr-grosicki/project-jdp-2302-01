package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts(){
        return new ArrayList<>();
    }

    @GetMapping(value = "/{productId}")
    public ProductDto getProduct(@PathVariable Long productId){
        return new ProductDto(1L, "Hoodie", new BigDecimal(129.97),
                "black with Kodilla logo");
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto){

    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return new ProductDto(1L, "Edit Hoodie", new BigDecimal(119.99),
                "black with BIG Kodilla logo");
    }

    @DeleteMapping(value = "/{productId}")
    public void deleteProduct(@PathVariable Long productId){

    }
}
