package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.GroupProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupMapper {

    public GroupProduct mapToGroupProduct (GroupProductDto groupProductDto){
        GroupProduct groupProduct = new GroupProduct();
        groupProduct.setId(groupProductDto.getId());
        groupProduct.setName(groupProductDto.getName());
        return groupProduct;
    }

    public GroupProductDto mapToGroupProductDto (GroupProduct groupProduct){
        return new GroupProductDto(
                groupProduct.getId(),
                groupProduct.getName());
    }

    public List<GroupProductDto> mapToGroupProductDtoList (List<GroupProduct> groupProductList){
        return groupProductList.stream()
                .map(this::mapToGroupProductDto)
                .collect(Collectors.toList());
    }
}
