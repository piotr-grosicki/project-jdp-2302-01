package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {
    @GetMapping
    public List<GroupProductDto> getProductGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/{groupId}")
    public GroupProductDto getProductGroup(@PathVariable Long groupId) {
        return new GroupProductDto(1L, "Sweatshirts");
    }

    @PostMapping
    public void createGroup(GroupProductDto groupProductDto) {

    }

    @PutMapping
    public GroupProductDto updateProductGroup(GroupProductDto groupProductDto) {
        return new GroupProductDto(1L, "Sweatshirt");
    }
}

