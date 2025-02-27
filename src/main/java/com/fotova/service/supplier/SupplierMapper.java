package com.fotova.service.supplier;

import com.fotova.dto.supplier.SupplierAddressDto;
import com.fotova.dto.supplier.SupplierDto;
import com.fotova.dto.supplier.SupplierProductDto;
import com.fotova.entity.SupplierEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierMapper {

    public SupplierDto mapToDto(SupplierEntity supplier){

        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setId(supplier.getId());
        supplierDto.setRegistrationNumber(supplier.getRegistrationNumber());

        if(supplier.getAddress() != null){

            SupplierAddressDto supplierAddressDto = new SupplierAddressDto();

            if(supplier.getAddress().getId() != null){
                supplierAddressDto.setId(supplier.getAddress().getId());
            }

            supplierAddressDto.setStreet(supplier.getAddress().getStreet());
            supplierAddressDto.setCity(supplier.getAddress().getCity());
            supplierAddressDto.setCountry(supplier.getAddress().getCountry());
            supplierAddressDto.setNumber(supplier.getAddress().getNumber());
            supplierDto.setSupplierAddressDto(supplierAddressDto);
        }

        if(supplier.getProduct() != null){
            SupplierProductDto supplierProductDto = new SupplierProductDto();

            if(supplier.getProduct().getId() != null){
                supplierProductDto.setId(supplier.getProduct().getId());
            }

            supplierProductDto.setName(supplier.getProduct().getName());
            supplierProductDto.setPrice(supplier.getProduct().getPrice());
            supplierProductDto.setQuantity(supplier.getProduct().getQuantity());
            supplierProductDto.setUrl(supplier.getProduct().getUrl());
            supplierDto.setSupplierProductDto(supplierProductDto);

        }

        return supplierDto;

    }

    public List<SupplierDto> mapToDtoList(List<SupplierEntity> suppliers){

        List<SupplierDto> supplierDtoList = new ArrayList<>();

        for(SupplierEntity supplier : suppliers){
            SupplierDto supplierDto = mapToDto(supplier);
            supplierDtoList.add(supplierDto);
        }

        return supplierDtoList;
    }
}
