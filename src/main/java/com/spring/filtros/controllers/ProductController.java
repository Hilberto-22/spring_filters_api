package com.spring.filtros.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.filtros.domain.Product;
import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.PageModel;
import com.spring.filtros.service.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> list(){
        List<Product> products = productService.list();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/listFilter")
    public ResponseEntity<PageModel<Product>> listFilter(@RequestParam Map<String, String> params){
        FilterModel filter = new FilterModel(params);
        PageModel<Product> pm = productService.list(filter);
        return ResponseEntity.ok(pm);
    }

}
