package ru.geekbraines.api.product.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException{

    private List<String>  errorFieldsMessages;

    public ValidationException(List<String> errorFieldsMessages){

        super(errorFieldsMessages.stream().collect(Collectors.joining(", ")));
        this.errorFieldsMessages=   errorFieldsMessages;
    }

}
