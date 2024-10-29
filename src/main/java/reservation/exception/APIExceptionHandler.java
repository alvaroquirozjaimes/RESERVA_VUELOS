package reservation.exception;// Paquete donde se encuentra la clase APIExceptionHandler

import org.springframework.http.HttpHeaders; // Importa HttpHeaders para manejar encabezados HTTP
import org.springframework.http.HttpStatusCode; // Importa HttpStatusCode para manejar códigos de estado HTTP
import org.springframework.http.ResponseEntity; // Importa ResponseEntity para construir respuestas HTTP
import org.springframework.validation.FieldError; // Importa FieldError para manejar errores de validación de campos
import org.springframework.web.bind.MethodArgumentNotValidException; // Importa la excepción para argumentos de método no válidos
import org.springframework.web.bind.annotation.ExceptionHandler; // Importa la anotación para manejar excepciones
import org.springframework.web.bind.annotation.RestControllerAdvice; // Importa la anotación para consejos de controladores REST
import org.springframework.web.context.request.WebRequest; // Importa WebRequest para manejar solicitudes web
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler; // Importa la clase base para manejar excepciones en controladores
import reservation.dto.ErrorDTO;
import reservation.enums.APIError;

import java.util.ArrayList; // Importa ArrayList para manejar listas de errores
import java.util.List; // Importa List para definir listas

@RestControllerAdvice // Anotación que indica que esta clase maneja excepciones globalmente para controladores REST
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    // Maneja excepciones específicas de Personal
    @ExceptionHandler(PersonalException.class) // Anotación para especificar qué excepción manejar
    public ResponseEntity<ErrorDTO> duplicateResource(PersonalException e, WebRequest request) {
        // Retorna una respuesta con el estado de la excepción y un ErrorDTO con la descripción y razones
        return ResponseEntity.status(e.getStatus()).body(new ErrorDTO(e.getDescription(), e.getReasons()));
    }

    // Maneja la excepción cuando un argumento de método no es válido
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> reasons = new ArrayList<>(); // Inicializa una lista para almacenar razones de errores

        // Itera sobre los errores de campo de la excepción y los agrega a la lista de razones
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            reasons.add(String.format("%s - %s", error.getField(), error.getDefaultMessage())); // Agrega el nombre del campo y el mensaje de error
        }

        // Retorna una respuesta con el estado de error de validación y un ErrorDTO con el mensaje y las razones
        return ResponseEntity.status(APIError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(APIError.VALIDATION_ERROR.getMessage(), reasons));
    }

}
