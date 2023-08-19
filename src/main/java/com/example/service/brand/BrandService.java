package com.example.service.brand;

import com.example.common.BaseResponse;
import com.example.payload.request.brand.BrandDTOCreate;
import com.example.payload.response.brand.BrandDTOResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BrandService {

    List<BrandDTOResponse> getAllBrand();

    BrandDTOResponse createBrand(BrandDTOCreate brandDTOCreate, MultipartFile image) throws IOException;

    BrandDTOResponse getBrandById(Long id);

    BrandDTOResponse updateBrand(BrandDTOCreate brandDTOCreate, MultipartFile image, Long id) throws IOException;

    String deleteBrand(Long id);

}
