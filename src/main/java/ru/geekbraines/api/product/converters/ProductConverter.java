package ru.geekbraines.api.product.converters;

import org.springframework.stereotype.Component;

import ru.geekbraines.api.product.data.Product;
import ru.geekbraines.api.product.dto.ProductDto;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto){

        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    } 


    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(),product.getCost());

    }



}
