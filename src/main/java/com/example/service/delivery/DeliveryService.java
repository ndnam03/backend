package com.example.service.delivery;

import com.example.entity.Delivery;

public interface DeliveryService {

    Delivery createDelivery(Delivery delivery);
    Delivery getDeliveryById(Long id);
}
