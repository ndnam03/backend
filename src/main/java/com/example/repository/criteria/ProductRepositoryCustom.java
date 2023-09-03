package com.example.repository.criteria;

import com.example.payload.request.product.ProductDTOFilter;
import com.example.payload.response.product.PageDTOResponse;

public interface ProductRepositoryCustom {

   PageDTOResponse findAllWithCustomPage(ProductDTOFilter  productDTOFilter);
}
