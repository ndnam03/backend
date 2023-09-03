package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="delivery_id")
    private Long id;

    @JsonFormat(pattern="dd/MM/yy")
    private Date creationDate;

    private String status;

    private String fullName;

    private String phoneNumber;

    private String address;

    @OneToMany(mappedBy = "delivery")
    @JsonIgnore
    private Set<Order> orders;
}
