package com.romanvoloboev.dto;

/**
 * @author romanvoloboev
 */
public class ErrorResponseDTO {
    private String errorMessage;

    public ErrorResponseDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
