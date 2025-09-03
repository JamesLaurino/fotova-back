package com.fotova.service.address;

import com.fotova.dto.address.AddressDto;
import com.fotova.entity.AddressEntity;
import com.fotova.exception.NotFoundException;
import com.fotova.repository.address.AddressRepositoryImpl;
import com.fotova.exception.DataExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
            log.error("Address with id " + id + " not found");
            throw new NotFoundException("Address with id " + id + " not found");
        }
        return addressMapper.mpaToAddressDto(addressEntity);
    }

    public String deleteAddressById(int id){
        if (addressRepository.findById(id) == null) {
            log.error("Address with id " + id + " not found." + " Deletion impossible for id : " + id);
            throw new NotFoundException("Address with id " + id + " not found. Deletion impossible.");
        }
        addressRepository.updateClientAddressId(id);
        addressRepository.deleteById(id);
        return "Address has been deleted successfully for id : " + id;
    }

    public AddressDto updateAddress(AddressDto addressDto){
        try {
            if (addressRepository.findById(addressDto.getId()) == null) {
                log.error("Address with id " + addressDto.getId() + " not found. Update impossible for id : " + addressDto.getId());
                throw new NotFoundException("Address with id " + addressDto.getId() + " not found. Update impossible for id : " + addressDto.getId() + " .");
            }
            AddressEntity addressEntity = addressMapper.mapToAddressEntity(addressDto);
            AddressEntity addressEntityRes= addressRepository.update(addressEntity);
            return addressMapper.mpaToAddressDto(addressEntityRes);
        } catch (DataExistException e) {
            throw new DataExistException("Address with id " + addressDto.getId() + " already exists.");
        }
    }

    public AddressDto addAddress(AddressDto addressDto){
        try {
            AddressEntity addressEntity = addressMapper.mapToAddressEntity(addressDto);
            addressEntity = addressRepository.save(addressEntity);
            return addressMapper.mpaToAddressDto(addressEntity);
        } catch (Exception e) {
            log.error("Error while saving address : " + e.getMessage());
            throw new DataExistException("Error while saving address");
        }
    }
}
