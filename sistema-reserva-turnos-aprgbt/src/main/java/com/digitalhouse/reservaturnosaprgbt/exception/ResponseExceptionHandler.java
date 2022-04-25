package com.digitalhouse.reservaturnosaprgbt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Manejar Exception ElementNotFoundException()
     */
    @ExceptionHandler(ElementNotFoundException.class)
    public final ResponseEntity<ExceptionResponse>
    manejoElementNotFoundException(ElementNotFoundException ex, WebRequest request) {

        ExceptionResponse er = ExceptionResponse.builder()
                .fecha(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalle(request.getDescription(false))
                .build();

        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);

    }

    /**
     * handleMethodArgumentNotValid (Argumentos en el RequestDto no validos) - (Spring-Validation)
     */
    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errorsMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorsMap.put(fieldName, errorMessage);
        });

        ExceptionResponse er = ExceptionResponse.builder()
                .fecha(LocalDateTime.now())
                .mensaje(errorsMap.toString())
                .detalle(request.getDescription(false))
                .build();

        return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
    }

    public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception ex, WebRequest request) {

        ExceptionResponse er = ExceptionResponse.builder()
                .fecha(LocalDateTime.now())
                .mensaje(ex.getMessage().toString())
                .detalle(request.getDescription(false))
                .build();

        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
