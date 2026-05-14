package com.example.role_based_authentication.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.role_based_authentication.shared.ErrorCode;
import com.example.role_based_authentication.shared.Result;
import com.example.role_based_authentication.shared.ResultMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Failed login attempted
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleException(BadCredentialsException ex, HttpServletRequest request) {
        ex.printStackTrace();
        return ResultMapper.toResponse(Result.Failure(
            ErrorCode.UNAUTHORIZED, 
            "Invalid username or password"
        ));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> handleException(AuthorizationDeniedException ex, HttpServletRequest request) {
        ex.printStackTrace();
        return ResultMapper.toResponse(Result.Failure(
            ErrorCode.ACCESS_DENIED, 
            "You don't have permission to execute this action"
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return ResultMapper.toResponse(Result.Failure(
            ErrorCode.INTERNAL_SERVER_ERROR, 
            "Something went wrong"
        ));
    }
}
