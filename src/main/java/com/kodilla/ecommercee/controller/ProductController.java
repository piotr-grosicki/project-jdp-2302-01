package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDbService productDbService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<Product> products = productDbService.getAllProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productDbService.getProduct(productId)));
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto){
        Product product = productMapper.mapToProduct(productDto);
        if (productDto.getGroupProductId() != null) {
            product.setGroupProduct(new GroupProduct()); //todo
        };
        productDbService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productDbService.saveProduct(product);
        return ResponseEntity.ok(productMapper.mapToProductDto(savedProduct));
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer productId) throws ProductNotFoundException {
        productDbService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
