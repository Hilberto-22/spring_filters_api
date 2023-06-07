package com.spring.filtros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.filtros.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
