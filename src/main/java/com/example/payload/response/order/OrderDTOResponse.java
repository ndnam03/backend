package com.example.payload.response.order;

import com.example.entity.ShoppingCart;
import com.example.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOResponse {

    private Long id;
    private String status;
    private User user;
    private ShoppingCart shoppingCart;
}
