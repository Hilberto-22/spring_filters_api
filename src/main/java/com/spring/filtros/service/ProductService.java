package com.spring.filtros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.filtros.domain.Product;
import com.spring.filtros.model.EqualFilterModel;
import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.PageModel;
import com.spring.filtros.repository.ProductRepository;
import com.spring.filtros.specification.ProductSpecification;

@Service
public class ProductService implements ListService<Product>{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> list() {
       List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public PageModel<Product> list(FilterModel filterModel) {
        Pageable pageable = filterModel.toPageable();

        Specification<Product> spec = null;

        List<EqualFilterModel> equalFilterModels = filterModel.gEqualFilterModels();

        if(!equalFilterModels.isEmpty()){
            EqualFilterModel firstEqualFilter = equalFilterModels.get(0);
            spec = ProductSpecification.equal(firstEqualFilter);
        }

        for(int i = 1; i < equalFilterModels.size(); i++){
            spec = spec.and(ProductSpecification.equal(equalFilterModels.get(i)));
        }

        Page<Product> productPage = productRepository.findAll(spec, pageable);
        PageModel<Product> pageModel = new PageModel<>(productPage);
        return pageModel;
    }
    
}
