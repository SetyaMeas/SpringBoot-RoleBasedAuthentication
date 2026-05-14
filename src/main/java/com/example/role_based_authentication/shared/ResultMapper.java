package com.example.role_based_authentication.shared;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResultMapper {

    public static <T> ResponseEntity<?> toResponse(Result<T> result) {
        if (result.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapResult(result));
        }

        switch (result.getErrorCode()) {
            case ALREADY_EXISTED:
                return ResponseEntity.status(HttpStatus.CONFLICT).body(mapResult(result));
            case NOT_FOUND:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapResult(result));
            case VALIDATION_ERROR:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapResult(result));
            case VIOLATION:
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(mapResult(result));
            case UNAUTHORIZED:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapResult(result));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapResult(result));
        }
    }

    private static <T> Map<String, Object> mapResult(Result<T> result) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getData());
        response.put("errorMessage", result.getErrorMessage());
        return response;
    }
}
