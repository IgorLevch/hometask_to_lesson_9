package ru.geekbraines.api.product.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbraines.api.product.dto.ProductDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("singleton")
public class Cart {

      private List<ProductDto> productDtoList;
      private int amount;
      
      @PostConstruct
      public void init(){
        productDtoList=new ArrayList<>();
      }


      public void addProduct(ProductDto productDto){
            productDtoList.add(productDto);
            amount+=1;

      }


      public List<ProductDto> showCart(){
            return productDtoList;

      } 


      public void delete(ProductDto productDto){
            productDtoList.remove(productDto);

      }


}
