package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.GroupProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupProductRepository extends CrudRepository<GroupProduct, Integer> {

    @Override
    List<GroupProduct> findAll();

    @Override
    Optional<GroupProduct> findById(Integer id);
}
