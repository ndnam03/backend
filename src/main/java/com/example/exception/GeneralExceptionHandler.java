package com.example.exception;

import com.example.common.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> handleOnlineShopException(NotFoundException ex){
        BaseResponse baseResponse = BaseResponse.builder()
                .code(String.valueOf(HttpStatus.NOT_FOUND.toString()))
                .data(ex.getLocalizedMessage())
                .success(false)
                .build();
        return new ResponseEntity<>(baseResponse,HttpStatus.NOT_FOUND);
    }
}
