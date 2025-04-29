package org.rajesh.jpaspecification.AppConf;

import org.rajesh.jpaspecification.exception.ResourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleNoSuchElementException(NoSuchElementException ex) {
        AppError appError = AppError.builder().
                status(HttpStatus.NOT_FOUND).
                message(ex.getMessage()).
                build();
        return buildApiResponse(appError);
    }

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleRunTimeException(ResourseNotFoundException ex) {
        AppError appError = AppError.builder().
                status(HttpStatus.NOT_FOUND).
                message(ex.getMessage()).
                build();
        return buildApiResponse(appError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalException(Exception ex) {
        AppError appError = AppError.builder().
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                message(ex.getMessage()).
                build();
        return buildApiResponse(appError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        AppError appError = AppError.builder().
                status(HttpStatus.BAD_REQUEST).
                message(errors.toString()).
                build();
        return buildApiResponse(appError);
    }

    public ResponseEntity<ApiResponse<?>> buildApiResponse(AppError error) {
        return new ResponseEntity<>(new ApiResponse<>(error), error.getStatus());
    }


}
