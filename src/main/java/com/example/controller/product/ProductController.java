package com.example.controller.product;


import com.example.payload.request.product.ProductDTOCreate;
import com.example.payload.request.product.ProductDTOFilter;
import com.example.payload.response.product.PageDTOResponse;
import com.example.payload.response.product.ProductDTOResponse;
import com.example.repository.criteria.ProductRepositoryCustomImpl;
import com.example.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:8080/")
public class ProductController {

    private final ProductService productService;

    private final ProductRepositoryCustomImpl productRepositoryCustom;

    @GetMapping("/getAll")
    public List<ProductDTOResponse> getAll() {
        return productService.getAll();
    }

    @PostMapping("/create")
    public ProductDTOResponse createProduct(@RequestParam("productName") String productName,
                                            @RequestParam("description") String description,
                                            @RequestParam("price") Double price,
                                            @RequestParam("quantity") Long quantity,
                                            @RequestParam("categoryId") Long categoryId,
                                            @RequestParam("brandId") Long brandId,
                                            @RequestParam("image") MultipartFile image
    ) throws IOException {


        ProductDTOCreate productDTOCreate = ProductDTOCreate.builder()
                .productName(productName)
                .price(price)
                .categoryId(categoryId)
                .brandId(brandId)
                .description(description)
                .quantity(quantity)
                .build();
        System.out.println(productDTOCreate);
        return productService.createProduct(productDTOCreate, image);

    }

    @GetMapping("/getAllWithPageCustom")
    public ResponseEntity<PageDTOResponse> getAllWithPageCustom
            (@RequestParam(defaultValue = "8", required = false, name = "size") int side,
             @RequestParam(defaultValue = "1", required = false, name = "page") int page,
             @RequestParam(defaultValue = "", required = false, name = "categoryId")List<Long> categoryId,
             @RequestParam(defaultValue = "", required = false, name = "brandId") List<Long> brandId,
             @RequestParam(defaultValue = "0", required = false, name = "priceFrom") Double priceFrom,
             @RequestParam(defaultValue = "", required = false, name = "priceTo") Double priceTo,
             @RequestParam(defaultValue = "asc", required = false, name = "sortByPrice") String sortByPrice,
             @RequestParam(defaultValue = "", required = false, name = "description") String description,
             @RequestParam(defaultValue = "", required = false, name = "name") String name) {

        ProductDTOFilter productDTOFilter = ProductDTOFilter.builder()
                .size(side)
                .page(page)
                .categoryId(categoryId)
                .brandId(brandId)
                .priceTo(priceTo)
                .priceFrom(priceFrom)
                .sortByPrice(sortByPrice)
                .description(description)
                .name(name)
                .build();
//        System.out.println(productRepositoryCustom.findAllWithCustomPage(productDTOFilter).getData().size());

        return ResponseEntity.ok(productRepositoryCustom.findAllWithCustomPage(productDTOFilter));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {

      return productService.deleteProduct(id);
    }

    @GetMapping("/getProductById/{id}")
    public ProductDTOResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update")
    public ProductDTOResponse updateProduct(@RequestParam("productName") String productName,
                                            @RequestParam("description") String description,
                                            @RequestParam("price") Double price,
                                            @RequestParam("quantity") Long quantity,
                                            @RequestParam("categoryId") Long categoryId,
                                            @RequestParam("brandId") Long brandId,
                                            @RequestParam("image") MultipartFile image,
                                            @RequestParam("id") Long id
    ) throws IOException {

        if(image.isEmpty()) {
            image = null;
        }

        ProductDTOCreate productDTOCreate = ProductDTOCreate.builder()
                .id(id)
                .productName(productName)
                .price(price)
                .categoryId(categoryId)
                .brandId(brandId)
                .description(description)
                .quantity(quantity)
                .build();
        System.out.println(productDTOCreate);
        return productService.updateProduct(productDTOCreate, image);

    }
}
