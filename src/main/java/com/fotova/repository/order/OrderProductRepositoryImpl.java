package com.fotova.repository.order;

import com.fotova.entity.OrderProductEntity;
import com.fotova.repository.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderProductRepositoryImpl implements ICrud<OrderProductEntity> {

    @Autowired
    private OrderProductRepositoryJpa orderProductRepositoryJpa;


    @Override
    public OrderProductEntity findById(int id) {
        return null;
    }

    @Override
    public List<OrderProductEntity> findAll() {
        return orderProductRepositoryJpa.findAll();
    }

    @Override
    public OrderProductEntity save(OrderProductEntity orderProductEntity) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public OrderProductEntity update(OrderProductEntity orderProductEntity) {
        return null;
    }
}
