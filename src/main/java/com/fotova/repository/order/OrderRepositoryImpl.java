package com.fotova.repository.order;

import com.fotova.entity.OrderEntity;
import com.fotova.repository.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements ICrud<OrderEntity> {

    @Autowired
    private OrderRepositoryJpa orderRepositoryJpa;

    @Override
    public OrderEntity findById(int id) {
        return null;
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepositoryJpa.findAll();
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(int id) {}

    @Override
    public OrderEntity update(OrderEntity orderEntity) {
        return null;
    }
}
