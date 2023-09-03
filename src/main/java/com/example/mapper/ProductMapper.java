package com.example.mapper;

import com.example.entity.Product;
import com.example.payload.response.category.CategoryDTOResponse;
import com.example.payload.response.product.ProductDTOResponse;

public class ProductMapper {


    public static ProductDTOResponse productDTOResponse(Product product) {
        return ProductDTOResponse.builder()
                .id(product.getId())
                .name(product.getProductName())
                .category(CategoryMapper.categoryDTOResponse(product.getCategory()))
                .brand(BrandMapper.toBrandDTOResponse(product.getBrand()))
                .image(product.getImage())
                .price(product.getPrice())
                .description(product.getDescription())
                .dateOfProduct(product.getDateOfProduct())
                .url(product.getUrl())
                .build();
    }
}
