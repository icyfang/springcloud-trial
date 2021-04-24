package com.example.businessinstance.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Hodur
 * @since 2021-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException{

    HttpStatus status;
    String errorCode;
}
