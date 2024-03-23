package ru.itmo.mega.cinema.agregator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itmo.mega.cinema.agregator.exception.EntityNotFoundException;
import ru.itmo.mega.cinema.agregator.model.response.ErrorMessageResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> internalErrorHandler(Exception e) {
        log.error("Произошла ошибка: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body(new ErrorMessageResponse(500, e.getMessage()));
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<?> notFoundErrorHandler(Exception e) {
        return ResponseEntity.notFound().build();
    }

}
