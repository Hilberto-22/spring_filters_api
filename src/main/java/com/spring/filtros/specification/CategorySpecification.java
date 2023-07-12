package com.spring.filtros.specification;

import org.springframework.data.jpa.domain.Specification;

import com.spring.filtros.builder.ExpressionBuilder;
import com.spring.filtros.domain.Category;
import com.spring.filtros.model.EqualFilterModel;
import com.spring.filtros.model.InFilterModel;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CategorySpecification {

    public static Specification<Category> equal(EqualFilterModel equalFilterModel){
        return new Specification<Category>(){

            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ExpressionBuilder<Category> exp = new ExpressionBuilder<>(Category.class); 
                
                Expression<Category> expression = exp.get(root, equalFilterModel.getColumn());

                Predicate predicate = null;

                if(expression != null){
                    predicate = (equalFilterModel.getIsEqual() ? criteriaBuilder.equal(expression, equalFilterModel.getValue())
                        : criteriaBuilder.notEqual(expression, equalFilterModel.getValue()));
                }
                return predicate; 
            }

        };
        
    }


    public static Specification<Category> in(InFilterModel inFilterModel){
        return new Specification<Category>(){

            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ExpressionBuilder<Category> exp = new ExpressionBuilder<>(Category.class); 
                
                Expression<Category> expression = exp.get(root, inFilterModel.getColumn());

                Predicate predicate = null;

                if(expression != null){
                  predicate = inFilterModel.getInEqual() ? expression.as(String.class).in(inFilterModel.getValue()) 
                    : expression.as(String.class).in(inFilterModel.getValue()).not();
                }
                return predicate; 
            }

        };
        
    }


}
