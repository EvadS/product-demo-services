package com.se.product.service.exception.advice;

import com.se.product.service.exception.AlreadyExistException;
import com.se.product.service.exception.InvalidTokenRequestException;
import com.se.product.service.exception.ResourceAlreadyInUseException;
import com.se.product.service.exception.UserLoginException;
import com.se.product.service.exception.model.ApiResponse;
import com.se.product.service.exception.model.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@RestControllerAdvice
public class AuthControllerAdvice {
    public static final String CONFLICT_MESSAGE = "Conflict in case of concurrent modification";
    public static final String TRACE = "trace";
    private static final Logger log = LoggerFactory.getLogger(AuthControllerAdvice.class);
    @Value("${reflectoring.trace:false}")
    private boolean printStackTrace;


    @ExceptionHandler(value = InvalidTokenRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ApiResponse handleInvalidTokenException(InvalidTokenRequestException ex, WebRequest request) {
        return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request));
    }

    @ExceptionHandler(value = ResourceAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse handleResourceAlreadyInUseException(ResourceAlreadyInUseException ex, WebRequest request) {
        return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request));
    }

    /**
     * Send a 409 Conflict in case of concurrent modification
     */
//    @ExceptionHandler({
//            ObjectOptimisticLockingFailureException.class,
//            OptimisticLockingFailureException.class,
//            DataIntegrityViolationException.class, DuplicateException.class})
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ApiResponse handleConflict(Exception ex, WebRequest request) {
//        log.error("Conflict in case of concurrent modification, {}", ex.getMessage());
//
//        return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request));
//    }
    @ExceptionHandler(value = UserLoginException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleUserLoginException(UserLoginException ex, WebRequest request) {
        return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request));
    }


    @ExceptionHandler({
            AlreadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity handleConflict(Exception ex, WebRequest request) {
        log.error(CONFLICT_MESSAGE, ex.getMessage());

        return buildErrorResponse(ex, HttpStatus.CONFLICT, request);
    }

    private String resolvePathFromWebRequest(WebRequest request) {
        try {
            return ((ServletWebRequest) request).getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
        } catch (Exception ex) {
            return null;
        }
    }

    /**************************************
     * buildErrorResponse Block
     **************************************/
    private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus,
                                                      WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus,
                                                      WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }
}
