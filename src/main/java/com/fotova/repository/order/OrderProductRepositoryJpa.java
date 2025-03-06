package com.fotova.repository.order;

import com.fotova.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepositoryJpa extends JpaRepository<OrderProductEntity,Integer> {
}
