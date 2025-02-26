package com.fotova.service.address;

import com.fotova.dto.address.AddressDto;
import com.fotova.entity.AddressEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressMapper {

    public AddressDto mpaToAddressDto(AddressEntity addressEntity){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressEntity.getId());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setNumber(addressEntity.getNumber());
        addressDto.setCountry(addressEntity.getCountry());
        addressDto.setStreet(addressEntity.getStreet());
        return addressDto;
    }

    public AddressEntity mapToAddressEntity(AddressDto addressDto){
        AddressEntity addressEntity = new AddressEntity();

        if(addressDto.getId() != null){
            addressEntity.setId(addressDto.getId());
        }

        addressEntity.setCity(addressDto.getCity());
        addressEntity.setNumber(addressDto.getNumber());
        addressEntity.setCountry(addressDto.getCountry());
        addressEntity.setStreet(addressDto.getStreet());
        return addressEntity;
    }

    public List<AddressDto> mpaToAddressDtoList(List<AddressEntity> addressEntityList) {

        List<AddressDto> addressDtoList = new ArrayList<>();

        for(AddressEntity addressEntity : addressEntityList){
            AddressDto addressDto = mpaToAddressDto(addressEntity);
            addressDtoList.add(addressDto);
        }

        return addressDtoList;
    }
}