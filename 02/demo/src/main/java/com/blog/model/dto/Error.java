package com.blog.model.dto;

/**
 * DTO (Data transport object) - for communication between services
 */
public class Error {

    private String errorCode;

    private String errorDescription;

    public Error() {}

    public Error(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
