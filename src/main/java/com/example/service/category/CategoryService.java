package com.example.service.category;

import com.example.payload.request.category.CategoryDTOCreate;
import com.example.payload.response.category.CategoryDTOResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryDTOResponse> getAll();
    CategoryDTOResponse createCategory(CategoryDTOCreate categoryDTOCreate);
    CategoryDTOResponse updateCategory(CategoryDTOCreate categoryDTOCreate);
    String deleteCategory(Long id);
    CategoryDTOResponse getCategoryById(Long id);
}
