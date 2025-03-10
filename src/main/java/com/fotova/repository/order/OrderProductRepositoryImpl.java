package com.fotova.repository.order;

import com.fotova.dto.orderProduct.OrderProductDto;
import com.fotova.entity.OrderProductEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderProductRepositoryImpl implements ICrud<OrderProductEntity> {

    @Autowired
    private OrderProductRepositoryJpa orderProductRepositoryJpa;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<OrderProductDto> getOrderProductByEmail(String email) {

        String sql = "SELECT\n" +
                "    order_entity.id,\n" +
                "    DATE_FORMAT(order_entity.create_at, '%d %M %Y'),\n" +
                "    product_entity.name,\n" +
                "    client_entity.email,\n" +
                "    order_product_entity.quantity_product,\n" +
                "    product_entity.price,\n" +
                "    product_entity.price * order_product_entity.quantity_product\n" +
                "FROM order_product_entity\n" +
                "INNER JOIN product_entity\n" +
                "INNER JOIN order_entity\n" +
                "INNER JOIN client_entity\n" +
                "WHERE product_entity.id = order_product_entity.product_id\n" +
                "AND order_entity.id = order_product_entity.order_id\n" +
                "AND client_entity.id = order_entity.client_id\n" +
                "AND client_entity.email = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, email);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results.stream().map(row -> {
            OrderProductDto orderProductDto = new OrderProductDto();

            orderProductDto.setOrderId(((Number) row[0]).intValue());
            orderProductDto.setCreationDate(row[1].toString());
            orderProductDto.setName(row[2].toString());
            orderProductDto.setEmail(row[3].toString());
            orderProductDto.setQuantity(((Number) row[4]).intValue());
            orderProductDto.setPrice(((Number) row[5]).doubleValue());
            orderProductDto.setTotal(((Number) row[6]).doubleValue());

            return orderProductDto;

        }).collect(Collectors.toList());
    }

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
        return orderProductRepositoryJpa.save(orderProductEntity);
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
