package com.example.controller.brand;

import com.example.entity.Brand;
import com.example.payload.request.brand.BrandDTOCreate;
import com.example.payload.response.brand.BrandDTOResponse;
import com.example.service.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8080/")
@RequestMapping("/api/brand")

public class BrandController {

    private final BrandService brandService;


    @GetMapping("/getAll")
    @ResponseBody
    public List<BrandDTOResponse> getAll() {

        return brandService.getAllBrand();
    }


    @PostMapping("/create")
    public ResponseEntity<BrandDTOResponse> createBrand(@RequestParam("name") String name,
                                                        @RequestParam("image") MultipartFile image) throws IOException {

        if(image.isEmpty()) {
            image = null;
        }

        BrandDTOCreate brandDTOCreate = BrandDTOCreate.builder()
                .name(name)
                .build();
        return  ResponseEntity.ok(brandService.createBrand(brandDTOCreate,image));

    }

    @DeleteMapping("/delete/{id}")
    public String deleteBrand(@PathVariable("id") Long id){
        return brandService.deleteBrand(id);
    }

    @GetMapping("/getById/{id}")
    public BrandDTOResponse getBrandById(@PathVariable("id") Long id){
        return brandService.getBrandById(id);
    }

    @PutMapping("/update")
    public BrandDTOResponse updateBrand(@RequestParam("name")String name,
                                        @RequestParam("id") Long id,
                                        @RequestParam(value = "image") MultipartFile image ) throws IOException {

        BrandDTOCreate brandDTOCreate = BrandDTOCreate.builder()
                .name(name)
                .build();
        return brandService.updateBrand(brandDTOCreate,image,id);
    }
}
