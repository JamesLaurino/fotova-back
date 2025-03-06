package com.fotova.service.order.helper;

import com.fotova.dto.orderProduct.OrderProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHelper {
    public Double computeBillingTotal(List<OrderProductDto> productDtoList){

        Double total = 0.0;

        for(OrderProductDto orderProductDto :productDtoList) {
            total += orderProductDto.getTotal();
        }

        return total;
    }
}