package com.spring.filtros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.filtros.domain.Supplier;
import com.spring.filtros.repository.SupplierRepository;

@Service
public class SupplierService implements ListService<Supplier>{

    @Autowired
    private SupplierRepository supplierRepository;
    
    @Override
    public List<Supplier> list() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }
    
}
