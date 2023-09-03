package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "comment")
public class Comment {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "comment_id")
      private Long id;

      private int rating;

      private String body;

      private Date createDate;

      @ManyToOne(fetch =  FetchType.LAZY)
      @JoinColumn(name = "user_id")
      User user;

      @ManyToOne(fetch =  FetchType.LAZY)
      @JsonIgnore
      @JoinColumn(name = "product_id")
      Product product;
}
