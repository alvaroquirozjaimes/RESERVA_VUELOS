package reservation.enums; // Paquete donde se encuentra la clase APIError

import org.springframework.http.HttpStatus; // Importa la clase HttpStatus de Spring

// Enumeración que representa los errores posibles de la API, con sus correspondientes códigos de estado HTTP y mensajes
public enum APIError {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "The are attributes with wrong values"), // Error de validación de atributos
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "The message not have a correct form"), // Error por formato incorrecto del mensaje
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reservation not found"), // Error cuando no se encuentra una reserva
    RESERVATION_WITH_SAME_ID(HttpStatus.BAD_REQUEST, "There is a reservation with the same id"), // Error por ID de reserva duplicado
    EXCEED_NUMBER_OPERATIONS(HttpStatus.TOO_MANY_REQUESTS, "You exceed the number of operations"); // Error por exceder el número de operaciones permitidas

    private final HttpStatus httpStatus; // Código de estado HTTP asociado al error
    private final String message; // Mensaje descriptivo del error

    // Constructor de la enumeración, que recibe el código de estado y el mensaje de error
    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus; // Asigna el código de estado HTTP
        this.message = message; // Asigna el mensaje de error
    }

    // Método getter para obtener el código de estado HTTP
    public HttpStatus getHttpStatus() {
        return httpStatus; // Devuelve el código de estado HTTP
    }

    // Método getter para obtener el mensaje de error
    public String getMessage() {
        return message; // Devuelve el mensaje de error
    }
}
