package com.example.controller.order;

import com.example.entity.Delivery;
import com.example.entity.Order;
import com.example.payload.request.order.OrderDTOCreate;
import com.example.payload.response.order.OrderDTOResponse;
import com.example.service.delivery.DeliveryService;
import com.example.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("http://localhost:8080/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderDTOCreate order) {

        return orderService.saveOrder(order);
    }

    @GetMapping("/userId/{id}")
    public OrderDTOResponse getOrderByUserId(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }
}
