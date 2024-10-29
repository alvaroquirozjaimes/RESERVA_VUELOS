package reservation.dto;

import jakarta.validation.constraints.NotBlank; // Importa la anotación para validar que los campos no estén en blanco
import reservation.validator.CityFormatConstraint;
// Importa la anotación personalizada para validar el formato de las ciudades

import java.util.Objects; // Importa la clase Objects para métodos de comparación y hash

// Clase Data Transfer Object (DTO) que representa un segmento de viaje
public class SegmentDTO {

    @CityFormatConstraint // Anotación personalizada para validar el formato de la ciudad de origen
    private String origin; // Ciudad de origen del viaje

    @CityFormatConstraint // Anotación personalizada para validar el formato de la ciudad de destino
    private String destination; // Ciudad de destino del viaje

    @NotBlank(message = "departure is mandatory") // Valida que el campo de salida no esté vacío
    private String departure; // Fecha y hora de salida

    @NotBlank(message = "arrival is mandatory") // Valida que el campo de llegada no esté vacío
    private String arrival; // Fecha y hora de llegada

    @NotBlank(message = "carrier is mandatory") // Valida que el campo de la aerolínea no esté vacío
    private String carrier; // Nombre de la aerolínea que opera el vuelo

    // Método getter para obtener la ciudad de origen
    public String getOrigin() {
        return origin; // Devuelve la ciudad de origen
    }

    // Método setter para establecer la ciudad de origen
    public void setOrigin(String origin) {
        this.origin = origin; // Asigna la ciudad de origen
    }

    // Método getter para obtener la ciudad de destino
    public String getDestination() {
        return destination; // Devuelve la ciudad de destino
    }

    // Método setter para establecer la ciudad de destino
    public void setDestination(String destination) {
        this.destination = destination; // Asigna la ciudad de destino
    }

    // Método getter para obtener la fecha y hora de salida
    public String getDeparture() {
        return departure; // Devuelve la fecha y hora de salida
    }

    // Método setter para establecer la fecha y hora de salida
    public void setDeparture(String departure) {
        this.departure = departure; // Asigna la fecha y hora de salida
    }

    // Método getter para obtener la fecha y hora de llegada
    public String getArrival() {
        return arrival; // Devuelve la fecha y hora de llegada
    }

    // Método setter para establecer la fecha y hora de llegada
    public void setArrival(String arrival) {
        this.arrival = arrival; // Asigna la fecha y hora de llegada
    }

    // Método getter para obtener el nombre de la aerolínea
    public String getCarrier() {
        return carrier; // Devuelve el nombre de la aerolínea
    }

    // Método setter para establecer el nombre de la aerolínea
    public void setCarrier(String carrier) {
        this.carrier = carrier; // Asigna el nombre de la aerolínea
    }

    // Método que compara si dos objetos SegmentDTO son iguales
    @Override
    public boolean equals(Object o) {
        if (this == o) // Compara si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass()) // Verifica si el objeto es nulo o de una clase diferente
            return false;
        SegmentDTO that = (SegmentDTO) o; // Convierte el objeto a SegmentDTO
        // Compara los atributos para determinar igualdad
        return Objects.equals(origin, that.origin) && Objects.equals(destination, that.destination)
                && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival)
                && Objects.equals(carrier, that.carrier);
    }

    // Método que genera un código hash para el objeto SegmentDTO
    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, departure, arrival, carrier); // Utiliza el método hash para los atributos relevantes
    }

    // Método que devuelve una representación en cadena del objeto SegmentDTO
    @Override
    public String toString() {
        return "SegmentDTO{" + "origin='" + origin + '\'' + ", destination='" + destination + '\'' + ", departure='"
                + departure + '\'' + ", arrival='" + arrival + '\'' + ", carrier='" + carrier + '\'' + '}';
        // Representa los atributos en formato de cadena
    }
}
