package com.spring.filtros.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EqualFilterModel {
    
    private String column;
    private String value;
    private Boolean isEqual;
}

