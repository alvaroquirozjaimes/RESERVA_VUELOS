package reservation.exception;// Paquete donde se encuentra la clase PersonalException

// Importa la enumeración APIError para manejar errores de la API
import org.springframework.http.HttpStatus; // Importa HttpStatus para manejar códigos de estado HTTP
import reservation.enums.APIError;

import java.util.List; // Importa List para manejar listas de razones

// Definición de la excepción personalizada PersonalException que extiende RuntimeException
public class PersonalException extends RuntimeException {
    private HttpStatus status; // Código de estado HTTP asociado a la excepción
    private String description; // Descripción del error
    private List<String> reasons; // Lista de razones que explican el error

    // Constructor que toma un APIError para inicializar la excepción
    public PersonalException(APIError error) {
        this.status = error.getHttpStatus(); // Inicializa el estado HTTP
        this.description = error.getMessage(); // Inicializa la descripción del error
    }

    // Constructor que permite establecer el estado, la descripción y las razones
    public PersonalException(HttpStatus status, String description, List<String> reasons) {
        this.status = status; // Inicializa el estado HTTP
        this.description = description; // Inicializa la descripción del error
        this.reasons = reasons; // Inicializa la lista de razones
    }

    // Método getter para obtener el estado HTTP
    public HttpStatus getStatus() {
        return status;
    }

    // Método setter para establecer el estado HTTP
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    // Método getter para obtener la descripción del error
    public String getDescription() {
        return description;
    }

    // Método setter para establecer la descripción del error
    public void setDescription(String description) {
        this.description = description;
    }

    // Método getter para obtener la lista de razones
    public List<String> getReasons() {
        return reasons;
    }

    // Método setter para establecer la lista de razones
    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }
}
