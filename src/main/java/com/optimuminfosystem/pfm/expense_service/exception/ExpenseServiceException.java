package com.optimuminfosystem.pfm.expense_service.exception;

import org.springframework.http.HttpStatus;

public class ExpenseServiceException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus status;

    public ExpenseServiceException(String errorCode, String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
