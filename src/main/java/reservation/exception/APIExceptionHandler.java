package reservation.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import reservation.dto.ErrorDTO;
import reservation.enums.APIError;

import java.util.ArrayList;
import java.util.List;

// Clase que maneja excepciones a nivel de controlador
@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    // Manejo de excepciones personalizadas
    @ExceptionHandler(PersonalException.class)
    public ResponseEntity<ErrorDTO> duplicateResource(PersonalException e, WebRequest request) {
        // Retorna un ResponseEntity con el estado de la excepción y un ErrorDTO que contiene la descripción y razones
        return ResponseEntity.status(e.getStatus()).body(new ErrorDTO(e.getDescription(), e.getReasons()));
    }

    // Manejo de excepciones de validación para argumentos de método no válidos
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // Lista para almacenar los mensajes de error
        List<String> reasons = new ArrayList<>();

        // Itera sobre los errores de campo y agrega mensajes a la lista
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            reasons.add(String.format("%s - %s", error.getField(), error.getDefaultMessage()));
        }

        // Retorna un ResponseEntity con el estado de error de validación y un ErrorDTO
        return ResponseEntity.status(APIError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(APIError.VALIDATION_ERROR.getMessage(), reasons));
    }

    // Manejo de excepciones de violación de restricciones de validación
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(ConstraintViolationException ex, WebRequest request) {
        // Lista para almacenar los mensajes de error
        List<String> reasons = new ArrayList<>();

        // Itera sobre las violaciones de restricciones y agrega mensajes a la lista
        for (ConstraintViolation<?> error : ex.getConstraintViolations()) {
            reasons.add(String.format("%s - %s", error.getPropertyPath(), error.getMessage()));
        }

        // Retorna un ResponseEntity con el estado de error de validación y un ErrorDTO
        return ResponseEntity.status(APIError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(APIError.VALIDATION_ERROR.getMessage(), reasons));
    }
}
