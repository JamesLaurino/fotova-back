package com.fotova.service.order;

import com.fotova.dto.order.OrderClientDto;
import com.fotova.dto.order.OrderDto;
import com.fotova.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderMapper {
    public List<OrderDto> mapToOrderDtoList(List<OrderEntity> orderEntities){
        List<OrderDto> orderDtoList = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntities){
            OrderDto orderDto = mapToOrderDto(orderEntity);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    public OrderDto mapToOrderDto(OrderEntity orderEntity){
        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderEntity.getId());
        orderDto.setCreateAt(orderEntity.getCreateAt());

        if(orderEntity.getClient()!=null){
            OrderClientDto orderClientDto = new OrderClientDto();
            orderClientDto.setId(orderEntity.getClient().getId());
            orderClientDto.setEmail(orderEntity.getClient().getEmail());
            orderDto.setClient(orderClientDto);
        }
        return orderDto;
    }
}
