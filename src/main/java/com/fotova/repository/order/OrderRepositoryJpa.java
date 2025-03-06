package com.fotova.repository.order;

import com.fotova.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryJpa extends JpaRepository<OrderEntity,Integer> {
}
