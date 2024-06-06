package com.ndungutse.tectalk.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ndungutse.tectalk.dto.ErrorDetails;

// One that send errors to the client
@ControllerAdvice
public class GlobalExceptionHandler {
        // Handle Specific Exception
        // THis @ used to handle specific errors
        @ExceptionHandler(ResourceNotFoundException.class)
        private ResponseEntity<ErrorDetails> handleResourceNotFound(
                        ResourceNotFoundException exception,
                        WebRequest webRequest) {

                ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                                webRequest.getDescription(false));

                return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BlogApiException.class)
        private ResponseEntity<ErrorDetails> handleApiExceptions(
                        BlogApiException exception,
                        WebRequest webRequest) {

                ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                                webRequest.getDescription(false));
                return new ResponseEntity<>(errorDetails, exception.getStatus());
        }

        // Handle global exceptions: Any other kind of exception not handled above
        @ExceptionHandler(Exception.class)
        private ResponseEntity<ErrorDetails> handleGlobalException(
                        Exception exception,
                        WebRequest webRequest) {
                ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                                webRequest.getDescription(false));

                // Log the excetion for debugging and send 500 server error

                return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
