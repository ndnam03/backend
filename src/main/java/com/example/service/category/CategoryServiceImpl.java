package com.example.service.category;

import com.example.entity.Category;
import com.example.exception.NotFoundException;
import com.example.mapper.CategoryMapper;
import com.example.payload.request.category.CategoryDTOCreate;
import com.example.payload.response.category.CategoryDTOResponse;
import com.example.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTOResponse> getAll() {
        List<CategoryDTOResponse> responseList = categoryRepository.findAll()
                .stream().map(category -> CategoryMapper.categoryDTOResponse(category)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public CategoryDTOResponse createCategory(CategoryDTOCreate categoryDTOCreate) {
        Category categoryCheck = categoryRepository.findByName(categoryDTOCreate.getName());
        if (categoryCheck != null) {
            throw new NotFoundException("Category name is not exits");
        }
        Category category = new Category();
        category.setName(categoryDTOCreate.getName());
//        System.out.println(category);
        categoryRepository.save(category);
        return CategoryMapper.categoryDTOResponse(category);
    }

    @Override
    public CategoryDTOResponse updateCategory(CategoryDTOCreate categoryDTOCreate) {
        Category category = categoryRepository.findById(categoryDTOCreate.getId())
                .orElseThrow(() -> new NotFoundException("Category is not exits"));
        category.setName(categoryDTOCreate.getName());
        categoryRepository.save(category);
        return CategoryMapper.categoryDTOResponse(category);
    }

    @Override
    public String deleteCategory(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID is not exits"));
        categoryRepository.deleteById(id);
        return "Successfully";
    }

    @Override
    public CategoryDTOResponse getCategoryById(Long id) {
        return null;
    }
}
