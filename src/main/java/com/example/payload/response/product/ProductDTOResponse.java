package com.example.payload.response.product;

import com.example.entity.Image;
import com.example.entity.ProductSize;
import com.example.payload.response.brand.BrandDTOResponse;
import com.example.payload.response.category.CategoryDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class ProductDTOResponse {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Date dateOfProduct;
    private CategoryDTOResponse category;
    private BrandDTOResponse brand;
    private Long quantity;
    private List<Image> url;
    private List<ProductSize> productSizes;

    public ProductDTOResponse(Long id, String name, String description, String image, Double price, Date dateOfProduct,
                              CategoryDTOResponse category, BrandDTOResponse brand, Long quantity, List<Image> url,
                              List<ProductSize> productSizes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.dateOfProduct = dateOfProduct;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
        this.url = url;
        this.productSizes = productSizes;
    }
}
