package ru.geekbraines.api.product.validators;

import org.springframework.stereotype.Component;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto){

        List<String> errors = new ArrayList<>();

        if (productDto.getCost()<1){
            errors.add("The cost can't be less than 1");
        }

        if (productDto.getTitle().isBlank()) {

            errors.add("Product can't have a blank title");
        }
        if (!errors.isEmpty()){
            throw new ValidationException (errors);



        }

    }


}
