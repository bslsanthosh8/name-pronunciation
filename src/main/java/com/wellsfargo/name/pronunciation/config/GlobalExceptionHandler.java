package com.wellsfargo.name.pronunciation.config;

import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.model.response.NamePronunciationResponse;
import com.wellsfargo.name.pronunciation.utils.Status;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        NamePronunciationResponse<Map> response = NamePronunciationResponse.<Map>builder().data(errors).status(Status.FAILED.name()).message("Input Validation Failed").build();
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleNamePronunciationException(NamePronunciationException ex) {
        return new ResponseEntity<>(NamePronunciationResponse.<String>builder().data(ex.getMessage()).status(ex.getStatus()).message(ex.getErrorType()).build(), HttpStatus.OK);
    }

}
