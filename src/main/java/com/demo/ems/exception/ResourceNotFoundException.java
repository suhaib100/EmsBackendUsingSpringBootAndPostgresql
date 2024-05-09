package com.demo.ems.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends RuntimeException {
    private Long employeeId;

    public ResourceNotFoundException(String message, Long employeeId){
        super(message);
        this.employeeId  = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
