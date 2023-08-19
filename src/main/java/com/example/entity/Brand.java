package com.example.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "brand", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"brand_name"})
})
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(name = "brand_name")
    private String name;

    @Lob
    private String image;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Product> product;
}
