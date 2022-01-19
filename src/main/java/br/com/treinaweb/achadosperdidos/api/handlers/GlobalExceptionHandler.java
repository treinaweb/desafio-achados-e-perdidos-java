package br.com.treinaweb.achadosperdidos.api.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.treinaweb.achadosperdidos.api.dtos.responses.ErrorResponse;
import br.com.treinaweb.achadosperdidos.core.services.storage.exceptions.StorageServiceException;
import br.com.treinaweb.achadosperdidos.core.services.token.exceptions.TokenServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final SnakeCaseStrategy snakeCaseStrategy = new SnakeCaseStrategy();

    @ExceptionHandler(TokenServiceException.class)
    public ResponseEntity<Object> handleTokenServiceException(
        TokenServiceException exception, HttpServletRequest request
    ) {
        return getResponseEntity(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
        EntityNotFoundException exception, HttpServletRequest request
    ) {
        return getResponseEntity(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(StorageServiceException.class)
    public ResponseEntity<Object> handleStorageServiceException(
        StorageServiceException exception, HttpServletRequest request
    ) {
        return getResponseEntity(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        var errors = getErrors(exception.getBindingResult().getFieldErrors());
        var path = ((ServletWebRequest)request).getRequest().getRequestURI();
        return getResponseEntity(status, "Houveram erros de validação", path, errors);
    }

    private HashMap<String, List<String>> getErrors(List<FieldError> validationErrors) {
        var errors = new HashMap<String, List<String>>();
        validationErrors.forEach(fieldError -> {
            var field = snakeCaseStrategy.translate(fieldError.getField());

            if (!errors.containsKey(field)) {
                var fieldErrors = new ArrayList<String>();
                fieldErrors.add(fieldError.getDefaultMessage());
                errors.put(field, fieldErrors);
            } else {
                errors.get(field).add(fieldError.getDefaultMessage());
            }
        });
        return errors;
    }

    private ResponseEntity<Object> getResponseEntity(
        HttpStatus status, String mensagem, String path, Map<String, List<String>> errors
    ) {
        var body = ErrorResponse.builder()
            .status(status.value())
            .timestamp(LocalDateTime.now())
            .mensagem(mensagem)
            .path(path)
            .errors(errors)
            .build();
        return new ResponseEntity<Object>(body, status);
    }

    private ResponseEntity<Object> getResponseEntity(HttpStatus status, String mensagem, String path) {
        return getResponseEntity(status, mensagem, path, null);
    }

}
