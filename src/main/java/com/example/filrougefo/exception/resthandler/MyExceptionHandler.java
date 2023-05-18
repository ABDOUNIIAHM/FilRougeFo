package com.example.filrougefo.exception.resthandler;

import com.example.filrougefo.exception.CategoryNotFoundException;
import com.example.filrougefo.exception.OrderNotFoundException;
import com.example.filrougefo.exception.ProductNotFoundException;
import com.example.filrougefo.exception.dto.DtoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = {CategoryNotFoundException.class})
    public ResponseEntity<Object> handleCategoryRequestException(CategoryNotFoundException e){

        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        DtoException dtoException = new DtoException(
                e.getMessage(),
                //e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(dtoException, badRequest);

    }
    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<Object> handleProductRequestException(ProductNotFoundException e){

        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        DtoException dtoException = new DtoException(
                e.getMessage(),
                //e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(dtoException, badRequest);

    }
    @ExceptionHandler(value = {OrderNotFoundException.class})
    public ResponseEntity<Object> handleOrderRequestException(OrderNotFoundException e){

        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        DtoException dtoException = new DtoException(
                e.getMessage(),
                //e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(dtoException, badRequest);

    }
}
