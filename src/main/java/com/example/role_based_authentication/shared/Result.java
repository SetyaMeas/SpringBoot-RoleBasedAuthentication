package com.example.role_based_authentication.shared;

import lombok.Getter;

public class Result<T> {
    
    @Getter
    private ErrorCode errorCode;

    @Getter
    private String errorMessage;

    @Getter
    private T data;

    private boolean success;

    // SUCCESS response constructor
    private Result(T data) {
        this.success = true;
        this.data = data;
        this.errorCode = null;
        this.errorMessage = null;
    }

    // FAILURE response constructor
    private Result(ErrorCode errorCode, String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.data = null;
    }

    public static <T> Result<T> Success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> Failure(ErrorCode errorCode, String errorMessage) {
        return new Result<>(errorCode, errorMessage);
    }

    public boolean isSuccess() {
        return this.success;
    }
}
