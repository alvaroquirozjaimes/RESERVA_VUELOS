package reservation.dto;

import java.time.LocalDate; // Importa la clase LocalDate para manejar fechas
import java.util.List; // Importa la clase List para manejar colecciones

public class ReservationDTO { // Clase que representa un objeto de transferencia de datos para reservas

    private Long id; // Identificador único de la reserva
    Long version; // Versión de la reserva para manejo de concurrencia
    private List<PassengerDTO> passengers; // Lista de pasajeros asociados a la reserva
    private ItineraryDTO itinerary; // Itinerario asociado a la reserva
    private LocalDate creationDate; // Fecha de creación de la reserva

    // Getter y Setter para la lista de pasajeros
    public List<PassengerDTO> getPassengers() {
        return passengers; // Devuelve la lista de pasajeros
    }

    public void setPassengers(List<PassengerDTO> passengers) {
        this.passengers = passengers; // Establece la lista de pasajeros
    }

    // Getter y Setter para el itinerario
    public ItineraryDTO getItinerary() {
        return itinerary; // Devuelve el itinerario
    }

    public void setItinerary(ItineraryDTO itinerary) {
        this.itinerary = itinerary; // Establece el itinerario
    }

    // Getter y Setter para el id
    public Long getId() {
        return id; // Devuelve el id de la reserva
    }

    public void setId(Long id) {
        this.id = id; // Establece el id de la reserva
    }

    // Getter y Setter para la fecha de creación
    public LocalDate getCreationDate() {
        return creationDate; // Devuelve la fecha de creación
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate; // Establece la fecha de creación
    }

    // Getter y Setter para la versión
    public Long getVersion() {
        return version; // Devuelve la versión
    }

    public void setVersion(Long version) {
        this.version = version; // Establece la versión
    }
}
