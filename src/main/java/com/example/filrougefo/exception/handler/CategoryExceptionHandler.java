package com.example.filrougefo.exception.handler;

import com.example.filrougefo.RestController.CategoryController;
import com.example.filrougefo.exception.CategoryControllerException;
import com.example.filrougefo.exception.dto.DtoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class CategoryExceptionHandler {
    @ExceptionHandler(value = {CategoryControllerException.class})
    public ResponseEntity<Object> handleCategoryRequestException(CategoryControllerException e){

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
