package com.fotova.service.order;

import com.fotova.dto.stripe.StripOrderBasket;
import com.fotova.dto.stripe.StripeProductRequest;
import com.fotova.dto.order.OrderBasketDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationOrderService {

    public static List<OrderBasketDto> orderBasketDtoList = new ArrayList<>();

    public static void setOrderBasketDtoList(OrderBasketDto orderBasketDto) {
        ValidationOrderService.orderBasketDtoList.add(orderBasketDto);
    }

    public static void setOrderBasketMapList(StripeProductRequest productRequest) {

        String uuid = productRequest.getName();
        String email = productRequest.getEmail();

        for(StripOrderBasket stripOrderBasket:productRequest.getProductBasket()) {
            OrderBasketDto orderBasketDto = new OrderBasketDto();
            orderBasketDto.setId(uuid);
            orderBasketDto.setProductId(stripOrderBasket.getProductId());
            orderBasketDto.setQuantity(stripOrderBasket.getQuantity());
            orderBasketDto.setEmail(email);
            ValidationOrderService.setOrderBasketDtoList(orderBasketDto);
        }
    }

    public static void cleanOrderBasketDtoList() {
        ValidationOrderService.orderBasketDtoList.clear();
    }
}
