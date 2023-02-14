package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductMapper {

    public Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return product;
    }

    public ProductDto mapToProductDto(Product product) {

        int groupProductId = 0;

        if (product.getGroupProduct() != null) {
            groupProductId = product.getGroupProduct().getId();
        }

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                groupProductId
        );
    }

    public List<Product> mapToProductList(List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
