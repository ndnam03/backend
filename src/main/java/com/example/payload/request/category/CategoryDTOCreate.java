package com.example.payload.request.category;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDTOCreate {

   private Long id;
    private String name;
}
