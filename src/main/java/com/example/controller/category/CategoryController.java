package com.example.controller.category;

import com.example.entity.Category;
import com.example.payload.request.category.CategoryDTOCreate;
import com.example.payload.response.category.CategoryDTOResponse;
import com.example.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<CategoryDTOResponse> getAll() {
        List<CategoryDTOResponse> categories = categoryService.getAll();
        return categories;
    }


    @PostMapping("/create")
    public CategoryDTOResponse createCategory(@RequestBody  CategoryDTOCreate categoryDTOCreate) {
//        System.out.println(categoryDTOCreate.toString());
        return categoryService.createCategory(categoryDTOCreate);
    }

    @PutMapping("/update")
    public CategoryDTOResponse updateCategory(@RequestBody CategoryDTOCreate categoryDTOCreate) {
       return categoryService.updateCategory(categoryDTOCreate);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

}
