package com.fotova.service.order;

import com.fotova.dto.order.OrderClientDto;
import com.fotova.dto.order.OrderDto;
import com.fotova.dto.orderProduct.OrderBillingDto;
import com.fotova.dto.orderProduct.OrderProductBillingDto;
import com.fotova.dto.orderProduct.OrderProductDto;
import com.fotova.entity.ClientEntity;
import com.fotova.entity.OrderEntity;
import com.fotova.service.order.helper.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderMapper {

    @Autowired
    private OrderHelper orderHelper;

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

    public OrderEntity mapToOrderEntity(OrderDto orderDto, Integer clientId){

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCreateAt(orderDto.getCreateAt());

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(clientId);
        clientEntity.setEmail(orderDto.getClient().getEmail());

        orderEntity.setClient(clientEntity);

        return orderEntity;
    }

    public OrderProductBillingDto  mapToOrderProductBillingDto(List<OrderProductDto> productDtoList)
    {
        OrderProductBillingDto  orderProductBillingDto = new OrderProductBillingDto();
        orderProductBillingDto.setOrderId(productDtoList.get(0).getOrderId());
        orderProductBillingDto.setCreationDate(productDtoList.get(0).getCreationDate());
        orderProductBillingDto.setTotal(orderHelper.computeBillingTotal(productDtoList));

        List<OrderBillingDto> productBillingDtoList  = new ArrayList<>();

        for(OrderProductDto orderProductDto : productDtoList){
            OrderBillingDto orderBillingDto = new OrderBillingDto();
            orderBillingDto.setName(orderProductDto.getName());
            orderBillingDto.setPrice(orderProductDto.getPrice());
            orderBillingDto.setQuantity(orderProductDto.getQuantity());
            productBillingDtoList.add(orderBillingDto);
        }

        orderProductBillingDto.setProductBillingDtoList(productBillingDtoList);

        return orderProductBillingDto;
    }
}
