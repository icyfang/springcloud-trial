package com.example.businessinstance.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Hodur
 * @date 2021-04-21
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        log.error("", e);
        log.error("【Global-Exception-Intercept】ApiException: code: {}, msg: {}", e.getStatus(), e.getErrorCode());
        return new ResponseEntity<>(new ErrorResponse(e.getStatus(), e.getErrorCode()), e.getStatus());
    }

    /**
     * @author Hodur
     * @date 2021-03-23
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorResponse {

        HttpStatus status;
        String errorCode;
    }
}
