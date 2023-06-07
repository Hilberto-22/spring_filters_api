package com.spring.filtros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.filtros.domain.Category;
import com.spring.filtros.repository.CategoryRepository;

@Service
public class CategoryService implements ListService<Category>{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> list() {
        List<Category> category = categoryRepository.findAll();
        return category;
    }
    
}
