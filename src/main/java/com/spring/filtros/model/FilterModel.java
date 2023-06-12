package com.spring.filtros.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import static com.spring.filtros.constant.ApiConstants.*;
import lombok.Getter;

@Getter
public class FilterModel {
    
    private Integer limit;
    private Integer page;
    private String sort;


    //construtor para captar os parametros que vem da url
    public FilterModel(Map<String, String> params){
        this.limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get(LIMIT_KEY)) : DEFAULT_LIMIT;
        this.page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get(PAGE_KEY)) : DEFAULT_PAGE;
        this.sort = params.containsKey(SORT_KEY) ? params.get(SORT_KEY) : DEFAULT_SORT;
    }

    public Pageable toPageable(){
        List<Order> orders = getOrders();

        return PageRequest.of(page, limit, Sort.by(orders));
    }

    private List<Order> getOrders(){

        List<Order> orders =  new ArrayList<>();

        //separa as propriedades por virgula
        String[] propStrings = sort.split(",");

        for (String prop : propStrings) {
            //verifica se uma propriedade não é vazia
            if(!prop.trim().isEmpty()){
                String  column = "";
    
                //se a propriedade comecar com - significa que a ordem é decrescente
                if(prop.startsWith("-")){
                    //remove o sinal por uma string vazia
                    column = prop.replace("-", "").trim();
                    //coloca na lista de orders
                    orders.add(Order.desc(column));
                }else{
                    column = prop.trim();
                    orders.add(Order.asc(column));
                }
            }
        }
        return orders;
    }

}
