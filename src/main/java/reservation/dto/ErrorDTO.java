package reservation.dto;

import java.util.List; // Importa la clase List para manejar colecciones de razones

// Clase que representa un objeto de error con una descripción y razones
public class ErrorDTO {
    private String description; // Descripción del error
    private List<String> reasons; // Lista de razones que explican el error

    // Constructor que inicializa la descripción y las razones del error
    public ErrorDTO(String description, List<String> reasons) {
        this.description = description; // Asigna la descripción
        this.reasons = reasons; // Asigna la lista de razones
    }

    // Método que devuelve la descripción del error
    public String getDescription() {
        return description; // Retorna la descripción
    }

    // Método que devuelve la lista de razones del error
    public List<String> getReasons() {
        return reasons; // Retorna la lista de razones
    }
}
