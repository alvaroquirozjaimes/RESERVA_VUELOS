package reservation.enums;

import org.springframework.http.HttpStatus; // Importa HttpStatus de Spring para manejar códigos de estado HTTP

// Enumeración que define diferentes tipos de errores de API
public enum APIError {
    // Definición de errores con su correspondiente código de estado HTTP y mensaje
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "There are attributes with wrong values"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "The message does not have a correct form"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reservation not found"),
    RESERVATION_WITH_SAME_ID(HttpStatus.BAD_REQUEST, "There is a reservation with the same id"),
    EXCEED_NUMBER_OPERATIONS(HttpStatus.TOO_MANY_REQUESTS, "You exceed the number of operations");

    private final HttpStatus httpStatus; // Código de estado HTTP asociado al error
    private final String message; // Mensaje descriptivo del error

    // Constructor de la enumeración que inicializa los valores de httpStatus y message
    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    // Método que retorna el código de estado HTTP
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    // Método que retorna el mensaje descriptivo del error
    public String getMessage() {
        return message;
    }
}
