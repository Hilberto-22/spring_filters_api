package com.spring.filtros.builder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.regex.Pattern;

import com.spring.filtros.constant.ApiConstants;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;

public class ExpressionBuilder<T> {

    Class<T> superClass;

    // construtor para receber a classe
    public ExpressionBuilder(Class<T> superClass) {
        this.superClass = superClass;
    }

    public Expression<T> get(Root<T> root, String field) {
        try {
            if (!field.contains(ApiConstants.DOT)) {
                if (containField(superClass, field)) {
                    return root.get(field);
                }
            } else {
                String[] fields = field.split(Pattern.quote(ApiConstants.DOT));

                if (fields.length == 2) {
                    String field1 = fields[0];
                    String field2 = fields[1];
                    
                    if (containField(superClass, field1)) {
                        Field classField = superClass.getDeclaredField(field1);
                        classField.setAccessible(true);

                        Class<?> subClass = classField.getType();

                        if(containField(subClass, field2))
                            return root.get(field1).get(field2);

                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // verificar se uma propriedade faz parte da classe
    private Boolean containField(Class<?> anyClass, String field) {
        Boolean contain = false;

        contain = Arrays.asList(anyClass.getDeclaredFields()).stream().anyMatch(df -> df.getName().equals(field));

        return contain;

    }

}
