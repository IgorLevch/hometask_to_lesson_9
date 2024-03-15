package ru.geekbraines.api.product.dto;

import lombok.Data;
import ru.geekbraines.api.product.data.Product;

@Data
public class ProductDto {


    private Long Id;

    private String title;

    private Long cost;

    private Integer mark;


    public ProductDto() {
    }

    public ProductDto(Long l, String ggh, Long l1, Integer i) {

    }


    public ProductDto(Product product){
        this.Id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.mark = product.getMark();
    }




}
