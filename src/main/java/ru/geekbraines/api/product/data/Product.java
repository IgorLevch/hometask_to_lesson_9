package ru.geekbraines.api.product.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbraines.api.product.dto.ProductDto;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")
@AllArgsConstructor
public class Product {

    // дефолтный конструктор входит в ломбок, но для этого ставим аннотацию @NoArgsConstructor -- хотя
    // она и так работает, но на всяк. случай можно поставить (это для Jackson), а так в ломбоке  стандратная аннотация - @Data
    // @AllArgsConstructor -- это конструктор со всеми объектами, чтобы можно было собирать сразу объект


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long Id;

    @Column(name="title",nullable = false,unique=true)
    private String title;

    @Column(name="cost")
    private Integer cost;






}
