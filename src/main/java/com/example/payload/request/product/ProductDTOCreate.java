package com.example.payload.request.product;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTOCreate {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Long categoryId;
    private Long brandId;
}
