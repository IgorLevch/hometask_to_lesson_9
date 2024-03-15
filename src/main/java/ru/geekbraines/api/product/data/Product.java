package ru.geekbraines.api.product.data;

import jakarta.persistence.*;
import lombok.Data;
import ru.geekbraines.api.product.dto.ProductDto;

@Data
@Entity
@Table(name="products")
public class Product {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(name="title",nullable = false,unique=true)
    private String title;

    @Column(name="cost")
    private Long cost;

    @Column(name="mark")
    private Integer mark;

    @Column(name="secret_key")
    private String secretKey;


    public Product(ProductDto productDto) {
    }


    public Product() {
    }

    public Product(Long id, String title, Long cost, Integer mark) {
        Id = id;
        this.title = title;
        this.cost = cost;
        this.mark = mark;
    }


    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", mark=" + mark +
                '}';
    }
}
