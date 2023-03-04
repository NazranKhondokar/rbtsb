package com.rbtsb.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public final ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
//        ErrorResponse response = new ErrorResponse(
//                new Date(),
//                ex.getMessage(),
//                request.getDescription(false),
//                HttpStatus.NOT_FOUND.value()
//        );
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
//
//    @Override
//    public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ErrorResponse response = new ErrorResponse(
//                new Date(),
//                ex.getMessage(),
//                "",
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        Map<String, String> errors = ex.getAllErrors().stream()
//                .filter(error -> !StringUtils.isEmpty(error.getDefaultMessage()))
//                .collect(Collectors.toMap(error -> ((FieldError) error).getField(), DefaultMessageSourceResolvable::getDefaultMessage,
//                        (existing, replacement) -> existing + " & " + replacement));
//
//        return badRequest().body(error(errors, "Bad Request").getJson());
//    }
}

