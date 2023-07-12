package com.spring.filtros.model;

import java.util.ArrayList;
import java.util.Arrays;
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
    private String equalFilters;
    private String inFilterModel;


    //construtor para captar os parametros que vem da url
    public FilterModel(Map<String, String> params){
        this.limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get(LIMIT_KEY)) : DEFAULT_LIMIT;
        this.page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get(PAGE_KEY)) : DEFAULT_PAGE;
        this.sort = params.containsKey(SORT_KEY) ? params.get(SORT_KEY) : DEFAULT_SORT;
        this.equalFilters = params.containsKey(EQUAL_FILTERS_KEY) ? params.get(EQUAL_FILTERS_KEY) : DEFAULT_EQUAL_FILTERS;
        this.inFilterModel = params.containsKey(IN_FILTERS_KEY) ? params.get(IN_FILTERS_KEY) : DEFAULT_IN_FILTERS;
    }

    public Pageable toPageable(){
        List<Order> orders = getOrders();

        return PageRequest.of(page, limit, Sort.by(orders));
    }

    public List<EqualFilterModel> gEqualFilterModels(){
        //lista inicia vazia
        List<EqualFilterModel> filterModels =  new ArrayList<EqualFilterModel>();
        
        //se a lista for igual a nula ou for vazia retorna a lista
        if(equalFilters == null || equalFilters.trim().isEmpty())
            return filterModels;

        //separa um vetor de string utilizando(;) 
        String[] filterParam = equalFilters.split(";");

        //para cada parametro da lista
        for(String param : filterParam){

            //se o parametro contem : significa igual
            if(param.contains(":")){

              String[] elements = param.split(":");
                if(elements.length ==2){
                    String column = elements[0];
                    String value = elements[1];

                    //adiciona um novo Objeto EqualFilterModel
                    filterModels.add(new EqualFilterModel(column, value, true));
                }
            
            }
            //se o parametro contem ~ significa diferenca
            if(param.contains("~")){

                String[] elements = param.split("~");
                if(elements.length ==2){
                    String column = elements[0];
                    String value = elements[1];

                    //adiciona um novo Objeto EqualFilterModel
                    filterModels.add(new EqualFilterModel(column, value, false));
                }
            }
        }

        return filterModels;
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

    public List<InFilterModel> getInFilters(){

        List<InFilterModel> inFilterModels =  new ArrayList<InFilterModel>();
        
        if(inFilterModel == null || inFilterModel.trim().isEmpty())
            return inFilterModels;

        String[] inFilterParam = inFilterModel.split(";");

        for(String param : inFilterParam){

            if(param.contains(":")){

              String[] elements = param.split(":");
                if(elements.length ==2){
                    String column = elements[0];
                    List<String> values = Arrays.asList(elements[1].split(","));

                    inFilterModels.add(new InFilterModel(column, values, true));
                }
            
            }
            if(param.contains("~")){

                String[] elements = param.split("~");
                if(elements.length ==2){
                    String column = elements[0];
                    List<String> values = Arrays.asList(elements[1].split(","));

                    inFilterModels.add(new InFilterModel(column, values, false));
                }
            }
        }
        return inFilterModels;

    }
}
