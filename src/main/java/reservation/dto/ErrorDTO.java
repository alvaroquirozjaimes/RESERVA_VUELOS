package reservation.dto;

import java.util.List;

// Clase Data Transfer Object (DTO) que representa un error en la aplicación
public class ErrorDTO {
    // Descripción del error
    private String description;

    // Lista de razones que explican el error
    private List<String> reasons;

    // Constructor que inicializa la descripción y las razones del error
    public ErrorDTO(String description, List<String> reasons) {
        this.description = description; // Asigna la descripción del error
        this.reasons = reasons; // Asigna la lista de razones para el error
    }

    // Método getter para obtener la descripción del error
    public String getDescription() {
        return description;
    }

    // Método getter para obtener la lista de razones del error
    public List<String> getReasons() {
        return reasons;
    }
}
