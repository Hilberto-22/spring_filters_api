package com.spring.filtros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.filtros.domain.Supplier;
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

}
