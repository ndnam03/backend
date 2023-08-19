package com.example.mapper;

import com.example.entity.Brand;
import com.example.payload.response.brand.BrandDTOResponse;

public class BrandMapper {

    public static BrandDTOResponse toBrandDTOResponse(Brand brand){
        return BrandDTOResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .image(brand.getImage())
                .build();
    }
}
