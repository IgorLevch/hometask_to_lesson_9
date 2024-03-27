package ru.geekbraines.api.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.geekbraines.api.product.cart.Cart;
import ru.geekbraines.api.product.converters.ProductConverter;
import ru.geekbraines.api.product.data.Product;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.exceptions.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
@Data
public class CartsService {

    private final Cart cart;
    private final ProductsService productsService;
    private final ProductConverter productConverter; 
    
    public void addProduct(Long Id){
        Product product = productsService.findById(Id).orElseThrow(() -> 
        new ResourceNotFoundException("Product not found by ID: "));
        cart.addProduct(productConverter.entityToDto(product));
    }

    public List<ProductDto> showCart(){
            return cart.showCart();

    }

    public void deleteById(Long Id){
        Product product = productsService.findById(Id).orElseThrow(() -> 
        new ResourceNotFoundException("Product not found by ID: "));
        cart.delete(productConverter.entityToDto(product));

    }


}
