package com.spring.filtros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.filtros.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
