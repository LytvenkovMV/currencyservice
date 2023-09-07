package com.example.mycurrencycomparator.controller;

import com.example.mycurrencycomparator.dto.error.ErrorDto;
import com.example.mycurrencycomparator.exception.CurrencyServiceException;
import com.example.mycurrencycomparator.exception.GifsServiceException;
import com.example.mycurrencycomparator.exception.WrongCodeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {WrongCodeException.class})
    public ResponseEntity<ErrorDto> handleWrongCodeEx() {
        ErrorDto errorDto = new ErrorDto(400, "Bad Request", "Валюта с таким кодом не найдена");
        return ResponseEntity
                .status(400)
                .body(errorDto);
    }

    @ExceptionHandler(value = {CurrencyServiceException.class})
    public ResponseEntity<ErrorDto> handleCurrencyServiceEx() {
        ErrorDto errorDto = new ErrorDto(503, "Service Unavailable"
                ,"Внешний сервис курсов валют не доступен. Проверьте адрес сервиса и ключ api в настройках приложения");
        return ResponseEntity
                .status(503)
                .body(errorDto);
    }

    @ExceptionHandler(value = {GifsServiceException.class})
    public ResponseEntity<ErrorDto> handleGifsServiceEx() {
        ErrorDto errorDto = new ErrorDto(503, "Service Unavailable"
                ,"Внешний сервис картинок не доступен. Проверьте адрес сервиса и ключ api в настройках приложения");
        return ResponseEntity
                .status(503)
                .body(errorDto);
    }
}
