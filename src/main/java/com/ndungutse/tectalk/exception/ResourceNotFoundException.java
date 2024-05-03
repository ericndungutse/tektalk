package com.ndungutse.tectalk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Causes spring boot to respond with the specified HTTP STATUS code whenever
// This exception is thrown from controller.
// So, When this exception is thrown, global error handler will attach 404 status code
@ResponseStatus(value = HttpStatus.NOT_FOUND) // 40
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldsName;
    private Long stringValue;

    public ResourceNotFoundException(String resourceName, String fieldsName, Long stringValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldsName, stringValue));
        this.resourceName = resourceName;
        this.fieldsName = fieldsName;
        this.stringValue = stringValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldsName() {
        return fieldsName;
    }

    public Long getStringValue() {
        return stringValue;
    }

}
