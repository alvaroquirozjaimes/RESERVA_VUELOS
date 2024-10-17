package reservation.model;

import jakarta.persistence.*;  // Importa las anotaciones de Jakarta Persistence
import java.util.Objects;  // Importa la clase Objects para operaciones sobre objetos

@Entity  // Indica que esta clase es una entidad de JPA
public class Segment extends Base {  // La clase Segment hereda de Base

    @Column(name = "origin", nullable = false, length = 3)  // Mapea la columna origin en la tabla, no puede ser nula y tiene un tamaño máximo de 3 caracteres
    private String origin;  // Ciudad de origen

    @Column(name = "destination", nullable = false, length = 3)  // Mapea la columna destination en la tabla, no puede ser nula y tiene un tamaño máximo de 3 caracteres
    private String destination;  // Ciudad de destino

    @Column(name = "departure", nullable = false)  // Mapea la columna departure en la tabla, no puede ser nula
    private String departure;  // Hora de salida

    @Column(name = "arrival", nullable = false)  // Mapea la columna arrival en la tabla, no puede ser nula
    private String arrival;  // Hora de llegada

    @Column(name = "carrier", nullable = false, length = 3)  // Mapea la columna carrier en la tabla, no puede ser nula y tiene un tamaño máximo de 3 caracteres
    private String carrier;  // Compañía aérea

    // Método para obtener la ciudad de origen
    public String getOrigin() {
        return origin;
    }

    // Método para establecer la ciudad de origen
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    // Método para obtener la ciudad de destino
    public String getDestination() {
        return destination;
    }

    // Método para establecer la ciudad de destino
    public void setDestination(String destination) {
        this.destination = destination;
    }

    // Método para obtener la hora de salida
    public String getDeparture() {
        return departure;
    }

    // Método para establecer la hora de salida
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    // Método para obtener la hora de llegada
    public String getArrival() {
        return arrival;
    }

    // Método para establecer la hora de llegada
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    // Método para obtener la compañía aérea
    public String getCarrier() {
        return carrier;
    }

    // Método para establecer la compañía aérea
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    // Método equals para comparar dos objetos de la clase Segment
    @Override
    public boolean equals(Object o) {
        if (this == o)  // Si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass())  // Si el objeto es nulo o de otra clase
            return false;
        Segment segment = (Segment) o;  // Convierte el objeto a tipo Segment
        // Compara los campos id, origin, destination, departure, arrival y carrier
        return Objects.equals(getId(), segment.getId()) &&
                Objects.equals(origin, segment.origin) &&
                Objects.equals(destination, segment.destination) &&
                Objects.equals(departure, segment.departure) &&
                Objects.equals(arrival, segment.arrival) &&
                Objects.equals(carrier, segment.carrier);
    }

    // Método hashCode para generar un código hash basado en id, origin, destination, departure, arrival y carrier
    @Override
    public int hashCode() {
        return Objects.hash(getId(), origin, destination, departure, arrival, carrier);
    }

    // Método toString para representar la clase como una cadena
    @Override
    public String toString() {
        return "Segment{" +
                "id=" + getId() +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", carrier='" + carrier + '\'' +
                '}';  // Representación en cadena del segmento
    }
}
