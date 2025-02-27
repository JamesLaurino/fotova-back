package com.fotova.service.supplier;

import com.fotova.dto.supplier.SupplierDto;
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
}
