package com.example.repository;

import com.example.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDeatilRepository extends JpaRepository<OrderDetail,Long> {
}
