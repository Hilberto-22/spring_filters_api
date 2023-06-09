package com.spring.filtros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.spring.filtros.domain.Supplier;
import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.PageModel;
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

    @Override
    public PageModel<Supplier> list(FilterModel filterModel) {
   
        Page<Supplier> supplierPage = supplierRepository.findAll(filterModel.toPageable());
        PageModel<Supplier> pageModel = new PageModel<>(supplierPage);
        return pageModel;
    }
}

