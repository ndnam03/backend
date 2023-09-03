package com.example.service.delivery;

import com.example.entity.Delivery;
import com.example.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

   private final DeliveryRepository deliveryRepository;

    @Override
    public Delivery createDelivery(Delivery delivery) {
        Delivery deliverySave = Delivery.builder()
                .creationDate(new Date())
                .address(delivery.getAddress())
                .fullName(delivery.getFullName())
                .phoneNumber(delivery.getPhoneNumber())
                .status("Đang xử lý")
                .build();
        return deliveryRepository.save(deliverySave);
    }

    @Override
    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id).orElseThrow(null);
    }
}
