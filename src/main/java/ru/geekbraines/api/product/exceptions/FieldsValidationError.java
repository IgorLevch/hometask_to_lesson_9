package ru.geekbraines.api.product.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldsValidationError {


    private List<String> errorFieldsMessages;  // мы возвращаем клиенту такую посылку : если мы хотим сообщить об ошибке, мы ее пакуем в
                     // обычную дж-сонину и кидаем в сторону клиента
                    // если что-то пошло не так, можем вернуть такой объект - но это очень неудобно (приходится писать много кода)
                    // гораздо более лучший подход - это кинуть понятный нам  exception (ResourceNotFoundException)



}
