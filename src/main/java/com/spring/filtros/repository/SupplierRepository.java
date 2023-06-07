package com.spring.filtros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.filtros.domain.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
}
