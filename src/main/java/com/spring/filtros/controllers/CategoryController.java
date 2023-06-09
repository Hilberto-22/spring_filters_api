package com.spring.filtros.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.filtros.domain.Category;
import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.PageModel;
import com.spring.filtros.service.CategoryService;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> list(){
        List<Category> categories = categoryService.list();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/listFilter")
    public ResponseEntity<PageModel<Category>> listFilter(@RequestParam Map<String, String> params){
        FilterModel filter = new FilterModel(params);
        PageModel<Category> pm = categoryService.list(filter);
        return ResponseEntity.ok(pm);
    }
}
