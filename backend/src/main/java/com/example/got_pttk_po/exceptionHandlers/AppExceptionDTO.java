package com.example.got_pttk_po.exceptionHandlers;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@ApiModel(description = "Response data for app exception")
public class AppExceptionDTO {

    @ApiModelProperty(notes = "HTTP status", example = "I_AM_A_TEAPOT")
    private HttpStatus status;
    @ApiModelProperty(notes = "Exception message", example = "Could not find user U53R")
    private String message;
    @ApiModelProperty(notes = "List of errors", example = "[\"com.example.got_pttk_po.exceptions.UserNotFoundException: Could not find user U53R\", \"com.example.got_pttk_po.exceptions.UserNotFoundException: Could not find user U53Rr\"]")
    private List<String> errors;

    public AppExceptionDTO(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public AppExceptionDTO(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}