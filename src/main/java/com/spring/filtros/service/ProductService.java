package com.spring.filtros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.filtros.domain.Product;
import com.spring.filtros.repository.ProductRepository;

@Service
public class ProductService implements ListService<Product>{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> list() {
       List<Product> products = productRepository.findAll();
        return products;
    }
    
}
