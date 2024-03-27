package ru.geekbraines.api.product.exceptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldsValidationError {

    private List<String> errorFieldsMEssage;

}
