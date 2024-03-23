package ru.geekbraines.api.product.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j   //идет со спрингБутом (можно не прописывать в ПОМе)
public class GlobalExceptionHandler {
  // это глобальный перехватчик исключений. Он перехватывает исключение, чтобы не упало наше приложение.
    @ExceptionHandler // аннотация указывает, что данный метод может перехватывать исключения какого-то вида (в нашем
    // случае ResourceNotFoundException). Мы перехватили это исключение и пакуем из него ResponseEntity
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e){
        log.error(e.getMessage(),e); // это надо сделать, чтобы самим знать данную ошибку
        //  класс ResponseEntity представляет собой настраиваемый Http-ответ.

        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),e.getMessage()),HttpStatus.NOT_FOUND);
    }   // NOT_ExceptionHandler // аннотация указывает, что данный метод может перехватывать исключения какого-то вида (в нашем
    // случае ResourceNotFoundException). Мы перехватили это исключение и пакуем из него ResponseEntity

    @ExceptionHandler
    public ResponseEntity<FieldsValidationError> catchValidationException(ValidationException e){
        log.error(e.getMessage(),e);

        return new ResponseEntity<>(new FieldsValidationError(e.getErrorFieldsMessages()),HttpStatus.BAD_REQUEST);
    }   // NOT_FOUND - это статус 404 . e.getMessage() -- эот мы добавляем





}
