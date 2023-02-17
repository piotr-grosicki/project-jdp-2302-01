package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.GroupProductDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupDbService groupDbService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupProductDto>> getProductGroups() {
        List<GroupProduct> groupProducts = groupDbService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupProductDtoList(groupProducts));
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<GroupProductDto> getProductGroup(@PathVariable Integer groupId) throws GroupNotFoundException{
        return ResponseEntity.ok(groupMapper.mapToGroupProductDto(groupDbService.getGroupProduct(groupId)));
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupProductDto groupProductDto) {
        GroupProduct groupProduct = groupMapper.mapToGroupProduct(groupProductDto);
        groupDbService.saveGroupProduct(groupProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<GroupProductDto> updateProductGroup(@RequestBody GroupProductDto groupProductDto) {
        GroupProduct groupProduct = groupMapper.mapToGroupProduct(groupProductDto);
        GroupProduct savedGroupProduct = groupDbService.saveGroupProduct(groupProduct);
        return ResponseEntity.ok(groupMapper.mapToGroupProductDto(savedGroupProduct));
    }

    @DeleteMapping(value = "/{groupId}")
    public ResponseEntity<Object> deleteGroupProduct(@PathVariable Integer groupId) throws GroupNotFoundException {
        if(groupDbService.getGroupProduct(groupId).getProducts().isEmpty()){
            groupDbService.deleteGroupProduct(groupId);}
        return ResponseEntity.ok().build();
    }
}

