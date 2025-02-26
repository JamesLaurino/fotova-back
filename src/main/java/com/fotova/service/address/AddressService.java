package com.fotova.service.address;

import com.fotova.dto.address.AddressDto;
import com.fotova.entity.AddressEntity;
import com.fotova.repository.address.AddressRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepositoryImpl addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    public List<AddressDto> getAllAddresses(){
        List<AddressEntity> addressEntities = addressRepository.findAll();
        return addressMapper.mpaToAddressDtoList(addressEntities);
    }

    public AddressDto getAddressById(int id){
        AddressEntity addressEntity = addressRepository.findById(id);
        return addressMapper.mpaToAddressDto(addressEntity);
    }
}
