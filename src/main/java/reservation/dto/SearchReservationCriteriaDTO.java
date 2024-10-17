package reservation.dto;

import java.time.LocalDate; // Importa la clase LocalDate para manejar fechas

// Clase que representa los criterios de búsqueda para las reservas
public class SearchReservationCriteriaDTO {

    // Atributo que almacena el ID del itinerario que se busca
    private Long itineraryId;

    // Atributo que almacena el nombre del pasajero (nombre de pila)
    private String firstName;

    // Atributo que almacena el apellido del pasajero
    private String lastName;

    // Atributo que almacena la fecha de la reserva
    private LocalDate reservationDate;

    // Atributo que almacena el campo por el cual se desea ordenar los resultados
    private String sortField;

    // Atributo que indica la dirección del ordenamiento (ascendente o descendente)
    private String sortingDirection;

    // Atributo que indica la página actual en la paginación (por defecto, 0)
    private Integer pageActual = 0;

    // Atributo que indica el tamaño de la página en la paginación (por defecto, 10)
    private Integer pageSize = 10;

    // Método para obtener el ID del itinerario
    public Long getItineraryId() {
        return itineraryId; // Retorna el ID del itinerario
    }

    // Método para establecer el ID del itinerario
    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId; // Asigna el valor al atributo itineraryId
    }

    // Método para obtener el nombre del pasajero
    public String getFirstName() {
        return firstName; // Retorna el primer nombre del pasajero
    }

    // Método para establecer el nombre del pasajero
    public void setFirstName(String firstName) {
        this.firstName = firstName; // Asigna el valor al atributo firstName
    }

    // Método para obtener el apellido del pasajero
    public String getLastName() {
        return lastName; // Retorna el apellido del pasajero
    }

    // Método para establecer el apellido del pasajero
    public void setLastName(String lastName) {
        this.lastName = lastName; // Asigna el valor al atributo lastName
    }

    // Método para obtener la fecha de la reserva
    public LocalDate getReservationDate() {
        return reservationDate; // Retorna la fecha de la reserva
    }

    // Método para establecer la fecha de la reserva
    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate; // Asigna el valor al atributo reservationDate
    }

    // Método para obtener el campo de ordenamiento
    public String getSortField() {
        return sortField; // Retorna el campo de ordenamiento
    }

    // Método para establecer el campo de ordenamiento
    public void setSortField(String sortField) {
        this.sortField = sortField; // Asigna el valor al atributo sortField
    }

    // Método para obtener la dirección de ordenamiento
    public String getSortingDirection() {
        return sortingDirection; // Retorna la dirección de ordenamiento
    }

    // Método para establecer la dirección de ordenamiento
    public void setSortingDirection(String sortingDirection) {
        this.sortingDirection = sortingDirection; // Asigna el valor al atributo sortingDirection
    }

    // Método para obtener la página actual
    public Integer getPageActual() {
        return pageActual; // Retorna la página actual
    }

    // Método para establecer la página actual
    public void setPageActual(Integer pageActual) {
        this.pageActual = pageActual; // Asigna el valor al atributo pageActual
    }

    // Método para obtener el tamaño de la página
    public Integer getPageSize() {
        return pageSize; // Retorna el tamaño de la página
    }

    // Método para establecer el tamaño de la página
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize; // Asigna el valor al atributo pageSize
    }
}
