package com.example.backexamen.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    private String error;
    private String message;
    private Map<String, String> details;
    
    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
