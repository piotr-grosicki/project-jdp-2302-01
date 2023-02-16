package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupProductRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertFalse;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupProductTest extends TestCase {

    @Autowired
    private GroupProductRepository groupProductRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final String GROUP_NAME = "Ubrania";

    @Test
    public void testSaveGroupProduct() {
        //Given
        GroupProduct groupProduct = new GroupProduct();
        groupProduct.setName(GROUP_NAME);

        //When
        groupProductRepository.save(groupProduct);

        //Then
        int id = groupProduct.getId();
        Optional<GroupProduct> readGroup = groupProductRepository.findById(id);
        assertTrue(readGroup.isPresent());
        assertEquals(GROUP_NAME,readGroup.get().getName());

        //CleanUp
        groupProductRepository.deleteById(id);
    }

    @Test
    public void testSaveGroupWithProducts() {
        //Given
        GroupProduct groupProduct = new GroupProduct();
        groupProduct.setName(GROUP_NAME);

        Product product1 = new Product();
        product1.setName("T-shirt");
        product1.setPrice(new BigDecimal(22.99));
        product1.setDescription("Czarny T-shirt z logo");

        Product product2 = new Product();
        product2.setName("Trousers");
        product2.setPrice(new BigDecimal(99.50));
        product2.setDescription("Spodnie czarne");

        groupProduct.getProducts().add(product1);
        groupProduct.getProducts().add(product2);

        //When
        groupProductRepository.save(groupProduct);
        int groupId = groupProduct.getId();
        int product1Id = product1.getId();
        int product2Id = product2.getId();

        //Then
        assertNotEquals(0, groupId);
        assertNotEquals(0, product1Id);
        assertNotEquals(0, product2Id);

        assertTrue(groupProductRepository.existsById(groupId));
        assertTrue(productRepository.existsById(product1Id));
        assertTrue(productRepository.existsById(product2Id));

        //CleanUp
        try {
            groupProductRepository.deleteById(groupId);
            productRepository.deleteById(product1Id);
            productRepository.deleteById(product2Id);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testFindAll() {
        //Given
        GroupProduct groupProduct1 = new GroupProduct();
        groupProduct1.setName("Naczynia");

        GroupProduct groupProduct2 = new GroupProduct();
        groupProduct2.setName("Sztućce");

        GroupProduct groupProduct3 = new GroupProduct();
        groupProduct3.setName("Pojemniki");

        //When
        groupProductRepository.save(groupProduct1);
        groupProductRepository.save(groupProduct2);
        groupProductRepository.save(groupProduct3);

        //Then
        assertEquals(3, groupProductRepository.findAll().size());

        //CleanUp
        try {
            groupProductRepository.deleteById(groupProduct1.getId());
            groupProductRepository.deleteById(groupProduct2.getId());
            groupProductRepository.deleteById(groupProduct3.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void findById(){
        //Given
        GroupProduct groupProduct = new GroupProduct();
        groupProduct.setName("Naczynia");

        //When
        groupProductRepository.save(groupProduct);
        int groupId = groupProduct.getId();

        //Then
        assertFalse(groupProductRepository.findById(groupId +1).isPresent());
        assertEquals("Naczynia", groupProductRepository.findById(groupId).get().getName());

        //CleanUp
        try {
            groupProductRepository.deleteById(groupId);
        } catch (Exception e) {
            //do nothing
        }
    }

}