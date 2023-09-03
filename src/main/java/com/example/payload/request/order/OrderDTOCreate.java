package com.example.payload.request.order;

import com.example.entity.Delivery;
import com.example.entity.User;
import com.example.payload.request.user.UserDTOCreate;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTOCreate {

    private Long id;

    private Date creationDate;

    private String status;

    private Long deliveryId;

    private Long userId;

}
