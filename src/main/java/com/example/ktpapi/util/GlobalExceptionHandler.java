package com.example.ktpapi.util;

import com.example.ktpapi.model.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<String> handleError(RuntimeException e){

        return new ApiResponse<>(
                false,
                e.getMessage(),
                null
        );
    }
}
