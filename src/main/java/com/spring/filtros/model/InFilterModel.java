package com.spring.filtros.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InFilterModel {
    private String column;
    private List<String> value;
    private Boolean inEqual;
}
