package com.spring.filtros.service;

import java.util.List;

import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.PageModel;

public interface ListService<T> {

    public List<T> list();

    public PageModel<T> list(FilterModel filterModel);
}
