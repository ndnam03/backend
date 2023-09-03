package com.example.controller.delivery;

import com.example.entity.Delivery;
import com.example.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8080/")
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/create")
    public Delivery deliveryCreate(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }
}
