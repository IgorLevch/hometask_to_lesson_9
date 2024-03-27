package ru.geekbraines.api.product.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.geekbraines.api.product.cart.Cart;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.service.CartsService;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartsService cartsService;

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long Id){
                cartsService.addProduct(Id);
    }

    @GetMapping()
    public List<ProductDto> showCart(){
        return cartsService.showCart();

    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long Id){
            cartsService.deleteById(Id);

    }




}
