package com.fotova.service.supplier;

import com.fotova.dto.address.AddressDto;
import com.fotova.dto.product.ProductDtoBack;
import com.fotova.dto.supplier.SupplierDto;
import com.fotova.entity.AddressEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.entity.SupplierEntity;
import com.fotova.repository.address.AddressRepositoryImpl;
import com.fotova.repository.product.ProductRepositoryImpl;
import com.fotova.repository.supplier.SupplierRepositoryImpl;
import com.fotova.service.address.AddressMapper;
import com.fotova.service.product.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepositoryImpl supplierRepositoryImpl;

    @Autowired
    private AddressRepositoryImpl addressRepositoryImpl;

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private ProductMapper productMapper;

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

    public SupplierDto update(SupplierDto supplierDto){
        SupplierEntity supplierEntity = supplierMapper.mapToEntity(supplierDto);
        supplierEntity = supplierRepositoryImpl.save(supplierEntity);
        return supplierMapper.mapToDto(supplierEntity);
    }

    public String delete(Integer id){
        supplierRepositoryImpl.updateSupplierAddressId(id);
        supplierRepositoryImpl.updateSupplierProductId(id);
        supplierRepositoryImpl.deleteById(id);
        return "Supplier has been deleted successfully";
    }

    public SupplierDto addSupplierAddress(Integer supplierId, AddressDto addressDto) {
        SupplierEntity supplierEntity = supplierRepositoryImpl.findById(supplierId);
        AddressEntity addressEntity = addressMapper.mapToAddressEntity(addressDto);
        addressRepositoryImpl.save(addressEntity);
        supplierEntity.setAddress(addressEntity);
        supplierRepositoryImpl.save(supplierEntity);

        return supplierMapper.mapToDto(supplierEntity);
    }

    public SupplierDto addSupplierProduct(Integer supplierId, ProductDtoBack productDtoBack) {
        SupplierEntity supplierEntity = supplierRepositoryImpl.findById(supplierId);
        ProductEntity productEntity = productMapper.mapToProductEntity(productDtoBack);
        productRepositoryImpl.save(productEntity);
        supplierEntity.setProduct(productEntity);
        supplierRepositoryImpl.save(supplierEntity);

        return supplierMapper.mapToDto(supplierEntity);
    }
}
