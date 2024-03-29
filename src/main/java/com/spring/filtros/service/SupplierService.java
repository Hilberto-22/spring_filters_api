package com.spring.filtros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.spring.filtros.domain.Supplier;
import com.spring.filtros.model.EqualFilterModel;
import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.InFilterModel;
import com.spring.filtros.model.PageModel;
import com.spring.filtros.repository.SupplierRepository;
import com.spring.filtros.specification.SupplierSpecification;

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
        Pageable pageable = filterModel.toPageable();

        Specification<Supplier> spec = null;

        List<EqualFilterModel> equalFilter = filterModel.gEqualFilterModels();
        List<InFilterModel> inFilter = filterModel.getInFilters();

        for(EqualFilterModel equalFilterModel : equalFilter){
            spec = spec.and(SupplierSpecification.equal(equalFilterModel));
        }

        for (InFilterModel inFilterModel : inFilter) {
            spec = spec.and(SupplierSpecification.in(inFilterModel));
        }

        Page<Supplier> supplierPage = supplierRepository.findAll(spec, pageable);
        PageModel<Supplier> pageModel = new PageModel<>(supplierPage);
        return pageModel;
    }
}

