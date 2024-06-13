package com.ndungutse.tectalk.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ndungutse.tectalk.dto.ErrorDetails;

// One that send errors to the client
// extends ResponseEntityExceptionHandler: Helps to add functionality of customizing validation errors
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
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

        // NB: Without Extending class, we can use above ways for
        // TODO: MethodArgumentNotValidException
        // Validation Errors by RespinEntityExceptionHandler
        @Override
        @Nullable
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers,
                        HttpStatusCode status,
                        WebRequest request) {

                Map<String, String> errors = new HashMap<>();

                ex.getBindingResult().getAllErrors().forEach((error) -> {
                        System.out.println("🔥🔥🔥🔥 EROR ******************" + error.getObjectName()
                                        + error.getDefaultMessage());
                        String fieldName = ((FieldError) error).getField();
                        String message = error.getDefaultMessage();

                        errors.put(fieldName, message);

                });

                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        // Handle global exceptions: Any other kind of exception not handled above
        @ExceptionHandler(Exception.class)
        private ResponseEntity<ErrorDetails> handleGlobalException(
                        Exception exception,
                        WebRequest webRequest) {

                System.out.println("************************8" + exception);
                ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                                webRequest.getDescription(false));

                // Log the excetion for debugging and send 500 server error
                return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
