package com.fotova.service.supplier;

import com.fotova.dto.supplier.SupplierDto;
import com.fotova.entity.SupplierEntity;
import com.fotova.repository.supplier.SupplierRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepositoryImpl supplierRepositoryImpl;

    @Autowired
    private SupplierMapper supplierMapper;

    public List<SupplierDto> findAll(){
        return supplierMapper.mapToDtoList(supplierRepositoryImpl.findAll());
    }

    public SupplierDto findById(Integer id){
        SupplierEntity supplierEntity = supplierRepositoryImpl.findById(id);
        return supplierMapper.mapToDto(supplierEntity);
    }

    public SupplierDto save(SupplierDto supplierDto){
        SupplierEntity supplierEntity = supplierMapper.mapToEntity(supplierDto);
        supplierEntity = supplierRepositoryImpl.save(supplierEntity);

        SupplierDto supplierDtoRes = new SupplierDto();
        supplierDtoRes.setId(supplierEntity.getId());
        supplierDtoRes.setRegistrationNumber(supplierEntity.getRegistrationNumber());

        return supplierDtoRes;
    }

}
