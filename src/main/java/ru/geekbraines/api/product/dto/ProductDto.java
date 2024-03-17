package ru.geekbraines.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbraines.api.product.data.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    // @AllArgsConstructor -- это конструктор со всеми объектами, чтобы можно было собирать сразу объект

    private Long Id;

    private String title;

    private Integer cost;


    public ProductDto(Product s) {
    }
}
