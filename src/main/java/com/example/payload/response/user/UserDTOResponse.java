package com.example.payload.response.user;

import com.example.entity.Order;
import com.example.entity.ShoppingCart;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResponse {

    private Long id;

    private String username;

    private String address;

    private String phoneNumber;

    private String image;

    private String role;

    private ShoppingCart shoppingCart;

    private List<Order> orderList;
}
