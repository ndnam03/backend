package com.example.service.order;


import com.example.entity.Order;
import com.example.payload.request.order.OrderDTOCreate;
import com.example.payload.response.order.OrderDTOResponse;

public interface OrderService {

    Order saveOrder(OrderDTOCreate order);
    Order updateOrder(Order  order, Long id);
    String deleteOrder(Long id);
    OrderDTOResponse getOrderById(Long id);
}
