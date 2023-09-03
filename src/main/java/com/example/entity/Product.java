package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String productName;

    private Date  dateOfProduct;

    private String description;

    private Long quantity;

    @Lob
    private String image;

    private Double price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "brand_id")
    private Brand brand;





    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Image> url;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Comment> productComment;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductSize> productSizes;

}
