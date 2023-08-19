package com.example.service.brand;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.entity.Brand;
import com.example.exception.NotFoundException;
import com.example.mapper.BrandMapper;
import com.example.payload.request.brand.BrandDTOCreate;
import com.example.payload.response.brand.BrandDTOResponse;
import com.example.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final Cloudinary cloudinary;

    @Override
    public List<BrandDTOResponse> getAllBrand() {
        List<BrandDTOResponse> brands = brandRepository.findAll()
                .stream().map(brand -> BrandMapper.toBrandDTOResponse(brand)).collect(Collectors.toList());
        return brands;
    }

    @Override
    public BrandDTOResponse createBrand(BrandDTOCreate brandDTOCreate, MultipartFile image) throws IOException {
        String imageBrand = null;

        if (image != null) {
            Map r = cloudinary.uploader().upload(image.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto",
                            "folder", "vue-spring/brand"));
            imageBrand = (String) r.get("secure_url");
        }
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDTOCreate,brand);
        brand.setImage(imageBrand);

        Brand brandSave = brandRepository.save(brand);

        return BrandMapper.toBrandDTOResponse(brandSave);
    }

    @Override
    public BrandDTOResponse getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Id is not exits"));

        return BrandMapper.toBrandDTOResponse(brand);
    }

    @Override
    public BrandDTOResponse updateBrand(BrandDTOCreate brandDTOCreate, MultipartFile image, Long id) throws IOException {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Id is not exits"));
        brand.setName(brandDTOCreate.getName());
        String imageBrand = null;
        if (image != null) {
            Map r = cloudinary.uploader().upload(image.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto",
                            "folder", "vue-spring/brand"));
            imageBrand = (String) r.get("secure_url");
            brand.setImage(imageBrand);
        }
        Brand brandSave = brandRepository.save(brand);
        return BrandMapper.toBrandDTOResponse(brandSave);
    }

    @Override
    public String deleteBrand(Long id) {
        brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Id is not exits"));
        brandRepository.deleteById(id);
        return "Successfully";
    }
}
