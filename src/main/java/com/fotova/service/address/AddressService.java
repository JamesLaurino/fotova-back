package com.fotova.service.address;

import com.fotova.dto.address.AddressDto;
import com.fotova.entity.AddressEntity;
import com.fotova.exception.NotFoundException;
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
        if (addressEntity == null) {
            throw new NotFoundException("Address with id " + id + " not found");
        }
        return addressMapper.mpaToAddressDto(addressEntity);
    }

    public String deleteAddressById(int id){
        if (addressRepository.findById(id) == null) {
            throw new NotFoundException("Address with id " + id + " not found");
        }
        addressRepository.updateClientAddressId(id);
        addressRepository.deleteById(id);
        return "Address has been deleted successfully for id : " + id;
    }

    public AddressDto updateAddress(AddressDto addressDto){
        if (addressRepository.findById(addressDto.getId()) == null) {
            throw new NotFoundException("Address with id " + addressDto.getId() + " not found");
        }
        AddressEntity addressEntity = addressMapper.mapToAddressEntity(addressDto);
        AddressEntity addressEntityRes= addressRepository.update(addressEntity);
        return addressMapper.mpaToAddressDto(addressEntityRes);
    }

    public AddressDto addAddress(AddressDto addressDto){
        AddressEntity addressEntity = addressMapper.mapToAddressEntity(addressDto);
        addressEntity = addressRepository.save(addressEntity);
        return addressMapper.mpaToAddressDto(addressEntity);
    }
}
