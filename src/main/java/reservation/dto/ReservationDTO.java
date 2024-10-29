package reservation.dto;

import jakarta.validation.Valid; // Importa la anotación para validar objetos anidados
import jakarta.validation.constraints.NotEmpty; // Importa la anotación para validar que la lista no esté vacía

import java.util.List; // Importa la clase List para manejar listas de pasajeros
import java.util.Objects; // Importa la clase Objects para métodos de comparación y hash

// Clase Data Transfer Object (DTO) que representa una reserva
public class ReservationDTO {

    private Long id; // Identificador único de la reserva

    @Valid // Indica que los elementos de la lista deben ser validados
    @NotEmpty(message = "You need at least one passenger") // Valida que la lista de pasajeros no esté vacía
    private List<PassengerDTO> passengers; // Lista de pasajeros asociados a la reserva

    @Valid // Indica que el objeto itinerario también debe ser validado
    private ItineraryDTO itinerary; // Itinerario asociado a la reserva

    // Método getter para obtener el identificador de la reserva
    public Long getId() {
        return id; // Devuelve el identificador de la reserva
    }

    // Método setter para establecer el identificador de la reserva
    public void setId(Long id) {
        this.id = id; // Asigna el identificador a la reserva
    }

    // Método getter para obtener la lista de pasajeros
    public List<PassengerDTO> getPassengers() {
        return passengers; // Devuelve la lista de pasajeros
    }

    // Método setter para establecer la lista de pasajeros
    public void setPassengers(List<PassengerDTO> passengers) {
        this.passengers = passengers; // Asigna la lista de pasajeros a la reserva
    }

    // Método getter para obtener el itinerario
    public ItineraryDTO getItinerary() {
        return itinerary; // Devuelve el itinerario de la reserva
    }

    // Método setter para establecer el itinerario
    public void setItinerary(ItineraryDTO itinerary) {
        this.itinerary = itinerary; // Asigna el itinerario a la reserva
    }

    // Método que compara si dos objetos ReservationDTO son iguales
    @Override
    public boolean equals(Object o) {
        if (this == o) // Compara si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass()) // Verifica si el objeto es nulo o de una clase diferente
            return false;
        ReservationDTO that = (ReservationDTO) o; // Convierte el objeto a ReservationDTO
        // Compara los atributos para determinar igualdad
        return Objects.equals(id, that.id) && Objects.equals(passengers, that.passengers)
                && Objects.equals(itinerary, that.itinerary);
    }

    // Método que genera un código hash para el objeto ReservationDTO
    @Override
    public int hashCode() {
        return Objects.hash(id, passengers, itinerary); // Utiliza el método hash para los atributos relevantes
    }

    // Método que devuelve una representación en cadena del objeto ReservationDTO
    @Override
    public String toString() {
        return "ReservationDTO{" + "id=" + id + ", passengers=" + passengers + ", itinerary=" + itinerary + '}';
        // Representa los atributos en formato de cadena
    }
}
