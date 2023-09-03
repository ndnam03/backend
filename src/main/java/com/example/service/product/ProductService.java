package com.example.service.product;

import com.example.payload.request.product.ProductDTOCreate;
import com.example.payload.response.product.ProductDTOResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductDTOResponse> getAll();

    ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate, MultipartFile image) throws IOException;

    ProductDTOResponse updateProduct(ProductDTOCreate productDTOCreate, MultipartFile image) throws  IOException;

    String deleteProduct(Long id);

    ProductDTOResponse getProductById(Long id);
}
