package com.kodilla.ecommercee.domain;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GROUP_PRODUCT")
public class GroupProduct {

    private Integer id;
    private String name;
    private List<Product> products = new ArrayList<>();

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupProduct",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<Product> getProducts() {
        return products;
    }

    private void setProducts(List<Product> products) {
        this.products = products;
    }
}
