package com.example.service.product;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.entity.Brand;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.exception.NotFoundException;
import com.example.mapper.BrandMapper;
import com.example.mapper.CategoryMapper;
import com.example.mapper.ProductMapper;
import com.example.payload.request.product.ProductDTOCreate;
import com.example.payload.response.product.ProductDTOResponse;
import com.example.repository.BrandRepository;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final Cloudinary cloudinary;

    @Override
    public List<ProductDTOResponse> getAll() {
        List<ProductDTOResponse> responseList = productRepository.findAll()
                .stream().map(product -> ProductMapper.productDTOResponse(product)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate, MultipartFile image) throws IOException {
        String imageProduct = null;

        if (image != null) {
            Map r = cloudinary.uploader().upload(image.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto",
                            "folder", "vue-spring/product"));
            imageProduct = (String) r.get("secure_url");
        }

        Category category = categoryRepository.findById(productDTOCreate.getCategoryId())
                .orElseThrow(() -> new NotFoundException("CategoryId is not exits"));

        Brand brand = brandRepository.findById(productDTOCreate.getBrandId())
                .orElseThrow(() -> new NotFoundException("BrandId is not exits"));
        Product product = new Product();
        BeanUtils.copyProperties(productDTOCreate,product);
        product.setImage(imageProduct);
        product.setCategory(category);
        product.setBrand(brand);
        product.setDateOfProduct(new Date());
        Product productSave = productRepository.save(product);
//        System.out.println(productSave);
        return ProductMapper.productDTOResponse(productSave);
    }

    @Override
    public ProductDTOResponse updateProduct(ProductDTOCreate productDTOCreate, MultipartFile image) throws IOException {
        String imageProduct = null;
        Product product = productRepository.findById(productDTOCreate.getId())
                .orElseThrow(() -> new NotFoundException("Product is not exits"));

        if (image != null) {
            Map r = cloudinary.uploader().upload(image.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto",
                            "folder", "vue-spring/product"));
            imageProduct = (String) r.get("secure_url");
        }else{
            imageProduct = product.getImage();
        }

        Category category = categoryRepository.findById(productDTOCreate.getCategoryId())
                .orElseThrow(() -> new NotFoundException("CategoryId is not exits"));

        Brand brand = brandRepository.findById(productDTOCreate.getBrandId())
                .orElseThrow(() -> new NotFoundException("BrandId is not exits"));
        BeanUtils.copyProperties(productDTOCreate,product);
        product.setImage(imageProduct);
        product.setCategory(category);
        product.setBrand(brand);
        product.setDateOfProduct(new Date());
        Product productSave = productRepository.save(product);
        return ProductMapper.productDTOResponse(productSave);
    }

    @Override
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product is not exits"));
        productRepository.delete(product);
        return "Successfully";
    }

    @Override
    public ProductDTOResponse getProductById(Long id) {
        ProductDTOResponse productDTOResponse = productRepository
                .findById(id)
                .map(product -> new ProductDTOResponse(
                        product.getId(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getImage(),
                        product.getPrice(),
                        product.getDateOfProduct(),
                        CategoryMapper.categoryDTOResponse(product.getCategory()),
                        BrandMapper.toBrandDTOResponse(product.getBrand()),
                        product.getQuantity(),
                        product.getUrl(),
                        product.getProductSizes()

                ))
                .orElseThrow(() -> new NotFoundException("Product is not exits"));
        return productDTOResponse;
    }
}
