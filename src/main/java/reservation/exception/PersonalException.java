package reservation.exception;

import org.springframework.http.HttpStatus;
import reservation.enums.APIError;

import java.util.List;

// Clase personalizada de excepción para manejar errores específicos de la aplicación
public class PersonalException extends RuntimeException {
    // Estado HTTP asociado con la excepción
    private HttpStatus status;

    // Descripción del error
    private String description;

    // Lista de razones o mensajes adicionales que explican el error
    private List<String> reasons;

    // Constructor que toma un objeto APIError para establecer el estado y la descripción
    public PersonalException(APIError error) {
        this.status = error.getHttpStatus(); // Asigna el estado HTTP
        this.description = error.getMessage(); // Asigna la descripción del error
    }

    // Constructor que permite establecer estado, descripción y razones personalizadas
    public PersonalException(HttpStatus status, String description, List<String> reasons) {
        this.status = status; // Asigna el estado HTTP
        this.description = description; // Asigna la descripción del error
        this.reasons = reasons; // Asigna las razones del error
    }

    // Métodos getters y setters para acceder y modificar los campos

    public HttpStatus getStatus() {
        return status; // Retorna el estado HTTP
    }

    public void setStatus(HttpStatus status) {
        this.status = status; // Permite modificar el estado HTTP
    }

    public String getDescription() {
        return description; // Retorna la descripción del error
    }

    public void setDescription(String description) {
        this.description = description; // Permite modificar la descripción del error
    }

    public List<String> getReasons() {
        return reasons; // Retorna la lista de razones del error
    }

    public void setReasons(List<String> reasons) {
        this.reasons = reasons; // Permite modificar la lista de razones del error
    }
}
