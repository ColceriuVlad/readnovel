package com.company.readnovel.handler;

import com.company.readnovel.exceptions.NoSuchEntityException;
import com.company.readnovel.exceptions.NotAuthenticatedException;
import com.company.readnovel.exceptions.NotValidatedException;
import com.company.readnovel.model.GenericResponse;
import com.company.readnovel.utils.ResponseUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    private ResponseUtils responseUtils;

    public ApplicationExceptionHandler(ResponseUtils responseUtils) {
        this.responseUtils = responseUtils;
    }

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<GenericResponse> handleNoSuchEntityException(NoSuchEntityException noSuchEntityException) {
        var errorResponse = responseUtils.getGenericErrorResponse(noSuchEntityException, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidatedException.class)
    public ResponseEntity<GenericResponse> handleNotValidatedException(NotValidatedException notValidatedException) {
        var errorResponse = responseUtils.getGenericErrorResponse(notValidatedException, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<GenericResponse> handleSqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        var errorResponse = responseUtils.getGenericErrorResponse(sqlIntegrityConstraintViolationException, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResponse> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        var errorResponse = responseUtils.getGenericErrorResponse(constraintViolationException, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<GenericResponse> handleNotAuthenticatedException(NotAuthenticatedException notAuthenticatedException) {
        var errorResponse = responseUtils.getGenericErrorResponse(notAuthenticatedException, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
