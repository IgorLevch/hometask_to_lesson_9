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


    public ProductDto(Product s) {  // минус такого подхода: если сделать конструктор на Продакт в ПРодакт ДТо
        // (собирающий сущность) , то у других сервисов возникнет вопрос: а что такое сущность (Продакт)? другие сервисы
        // же не знают, что это такое -- и видят только Продакт Дто
        //поэтому так лучще не делать : ДТО шка к сущности привязываться не может -- просто из-за того, что мы не можем ссылку
               // на сущность отдавать во внешние сервисы

    }
}
