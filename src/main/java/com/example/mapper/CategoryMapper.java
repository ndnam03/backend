package com.example.mapper;

import com.example.entity.Category;
import com.example.payload.request.category.CategoryDTOCreate;
import com.example.payload.response.category.CategoryDTOResponse;

public class CategoryMapper {

    public static CategoryDTOResponse categoryDTOResponse(Category category) {
        return CategoryDTOResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
