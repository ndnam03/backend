package com.example.payload.request.product;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTOCreate {

    private Long id;
    private String productName;
    private String description;
    private String image;
    private Double price;
    private Date dateOfProduct;
    private Long categoryId;
    private Long brandId;
    private Long quantity;
}
