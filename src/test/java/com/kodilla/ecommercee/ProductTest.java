package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupProductRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTest extends TestCase {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupProductRepository groupProductRepository;
    @Autowired
    private CartRepository cartRepository;


    @Test
    public void testProductSave(){
        //Given
        Product product = new Product();

        product.setName("Kubek");
        product.setPrice(new BigDecimal("23.17"));
        product.setDescription("Czarny bez ucha");

        //When
        productRepository.save(product);

        //Then
        int productId = product.getId();
        Optional<Product> readProduct = productRepository.findById(productId);
        assertTrue(readProduct.isPresent());

        //CleanUp
        try {
            productRepository.deleteById(productId);
        }catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testSaveProductInGroupProduct(){
        //Given
        GroupProduct groupProduct = new GroupProduct();
        groupProduct.setName("Naczynia");

        Product product = new Product();
        product.setName("Kubek");
        product.setPrice(new BigDecimal("22.99"));
        product.setDescription("Czarny bez ucha");
        product.setGroupProduct(groupProduct);

        //When
        groupProductRepository.save(groupProduct);
        productRepository.save(product);

        int groupId = groupProduct.getId();
        int productId = product.getId();

        //Then
        assertNotEquals(0, groupId);
        assertNotEquals(0, productId);

        assertTrue(groupProductRepository.existsById(groupId));
        assertTrue(productRepository.existsById(productId));

        assertNotNull(groupProductRepository.findById(groupId).get().getProducts());

        //CleanUp
        try {
            groupProductRepository.deleteById(groupId);
            productRepository.deleteById(productId);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testSaveManyProductsInCart(){
        //Given
        Cart cart = new Cart();
        cart.setStatus(true);

        Product product1 = new Product();
        product1.setName("Kubek");
        product1.setPrice(new BigDecimal("22.99"));
        product1.setDescription("Czarny bez ucha");

        Product product2 = new Product();
        product2.setName("Trousers");
        product2.setPrice(new BigDecimal("99.50"));
        product2.setDescription("Spodnie czarne");

        //When
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cartRepository.save(cart);

        int product1Id = product1.getId();
        int product2Id = product2.getId();
        int cartId = cart.getId();

        //Then
        assertNotEquals(0, cartId);
        assertTrue(cartRepository.existsById(cartId));
        assertTrue(productRepository.existsById(product1Id));
        assertTrue(productRepository.existsById(product2Id));

        //CleanUp
        try {
            cartRepository.deleteById(cartId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
