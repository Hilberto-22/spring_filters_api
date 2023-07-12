package com.spring.filtros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.filtros.domain.Category;
import com.spring.filtros.model.EqualFilterModel;
import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.InFilterModel;
import com.spring.filtros.model.PageModel;
import com.spring.filtros.repository.CategoryRepository;
import com.spring.filtros.specification.CategorySpecification;

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
        Pageable pageable = filterModel.toPageable();


        Specification<Category> spec = Specification.where(null);

        List<EqualFilterModel> equalFilters = filterModel.gEqualFilterModels();
        List<InFilterModel> inFilters = filterModel.getInFilters();


        for (EqualFilterModel equalFilterModel : equalFilters) {
            spec = spec.and(CategorySpecification.equal(equalFilterModel));
        }

        for (InFilterModel inFilterModel : inFilters) {
            spec = spec.and(CategorySpecification.in(inFilterModel));
        }

        Page<Category> categoryPage = categoryRepository.findAll(spec, pageable);
        PageModel<Category> pageModel = new PageModel<>(categoryPage);
        return pageModel;
    }
    
}
