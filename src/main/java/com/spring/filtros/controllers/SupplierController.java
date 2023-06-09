package com.spring.filtros.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.filtros.domain.Supplier;
import com.spring.filtros.model.FilterModel;
import com.spring.filtros.model.PageModel;
import com.spring.filtros.service.SupplierService;

@RestController
@RequestMapping(value = "suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> list(){
        List<Supplier> suppliers = supplierService.list();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/listFilter")
    public ResponseEntity<PageModel<Supplier>> listFilter(@RequestParam Map<String, String> params){
        FilterModel filter = new FilterModel(params);
        PageModel<Supplier> pm = supplierService.list(filter);
        return ResponseEntity.ok(pm);
    }
}
