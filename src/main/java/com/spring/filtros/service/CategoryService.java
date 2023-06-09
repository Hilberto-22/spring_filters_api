package com.spring.filtros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.spring.filtros.domain.Category;
import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.PageModel;
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

    @Override
    public PageModel<Category> list(FilterModel filterModel) {
        
        Page<Category> categoryPage = categoryRepository.findAll(filterModel.toPageable());
        PageModel<Category> pageModel = new PageModel<>(categoryPage);
        return pageModel;
    }
    
}
