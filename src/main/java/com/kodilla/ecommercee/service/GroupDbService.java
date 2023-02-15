package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.GroupNotFoundException;
import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.repository.GroupProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupDbService {

    private final GroupProductRepository groupRepository;

    public List<GroupProduct> getAllGroups(){
        return groupRepository.findAll();
    }

    public GroupProduct getGroupProduct(Integer id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    public GroupProduct saveGroupProduct(GroupProduct groupProduct) {
        return groupRepository.save(groupProduct);
    }

    public void deleteGroupProduct (Integer id) throws GroupNotFoundException {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new GroupNotFoundException();
        }
    }
}
