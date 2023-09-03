package com.example.service.order;

import com.example.entity.Delivery;
import com.example.entity.Order;
import com.example.entity.ShoppingCart;
import com.example.entity.User;
import com.example.exception.NotFoundException;
import com.example.payload.request.order.OrderDTOCreate;
import com.example.payload.response.order.OrderDTOResponse;
import com.example.repository.DeliveryRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ShoppingCartRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    @Override
    public Order saveOrder(OrderDTOCreate orderDTOCreate) {

        Delivery delivery = deliveryRepository.findById(orderDTOCreate.getDeliveryId())
                .orElseThrow(() -> new NotFoundException("Delivery is not exits"));
        User user = userRepository.findById(orderDTOCreate.getUserId())
                .orElseThrow(() -> new NotFoundException("User is not exits"));
        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.setStatus("Đã xác nhận");
        shoppingCart.setTotalPrices(0.0);
        shoppingCart.setTotalItems(0l);
        shoppingCart.getCartItems().forEach(o -> {
            o.setStatus(false);
        });
        shoppingCartRepository.save(shoppingCart);
        Order orderSave = Order.builder()
                .creationDate(new Date())
                .status("Đang xử lý")
                .delivery(delivery)
                .user(user)
                .build();

        return orderRepository.save(orderSave);
    }

    @Override
    public Order updateOrder(Order order, Long id) {
        return null;
    }

    @Override
    public String deleteOrder(Long id) {
        return null;
    }

    @Override
    public OrderDTOResponse getOrderById(Long id) {

        User user = userRepository.findById(id).orElseThrow(null);


        OrderDTOResponse orderDTOResponse = OrderDTOResponse.builder()
                .shoppingCart(user.getShoppingCart())
                .status(user.getShoppingCart().getStatus())
                .user(user)
                .build();

        return orderDTOResponse;
    }
}
