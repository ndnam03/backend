package com.example.payload.request.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDTOFilter {

    private Integer size;
    private Integer page;
    private List<Long> categoryId;
    private List<Long> brandId;
    private Double priceFrom;
    private Double priceTo;
    private String sortByPrice;
    private String description;
    private String name;
}
