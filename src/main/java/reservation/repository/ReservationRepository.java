package reservation.repository;// Especifica el paquete al que pertenece la clase.

import org.springframework.stereotype.Component; // Importa la anotación Component de Spring.
import reservation.model.*;

import java.math.BigDecimal; // Importa BigDecimal para trabajar con precios.
import java.time.LocalDate; // Importa LocalDate para manejar fechas.
import java.util.*; // Importa las colecciones de Java.

@Component // Marca esta clase como un componente de Spring, lo que permite su inyección de dependencias.
public class ReservationRepository {

    // Lista estática que almacenará las reservas en memoria.
    static List<Reservation> reservations = new ArrayList<>();

    // Bloque estático para inicializar datos de ejemplo.
    static {
        // Crea un nuevo pasajero y establece sus atributos.
        Passenger passenger = new Passenger();
        passenger.setFirstName("Alvaro");
        passenger.setLastName("Quiroz");
        passenger.setId(1L);
        passenger.setDocumentType("DNI");
        passenger.setDocumentNumber("12345678");
        passenger.setBirthday(LocalDate.of(1985, 1, 1));

        // Crea un nuevo precio y establece sus atributos.
        Price price = new Price();
        price.setBasePrice(BigDecimal.ONE);
        price.setTotalTax(BigDecimal.ZERO);
        price.setTotalPrice(BigDecimal.ONE);

        // Crea un nuevo segmento y establece sus atributos.
        Segment segment = new Segment();
        segment.setArrival("2025-01-01");
        segment.setDeparture("2024-12-31");
        segment.setOrigin("EZE");
        segment.setDestination("MIA");
        segment.setCarrier("AA");
        segment.setId(1L);

        // Crea un nuevo itinerario y establece sus atributos.
        Itinerary itinerary = new Itinerary();
        itinerary.setId(1L);
        itinerary.setPrice(price);
        itinerary.setSegment(List.of(segment)); // Agrega el segmento a la lista del itinerario.

        // Crea una nueva reserva y establece sus atributos.
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPassengers(List.of(passenger)); // Agrega el pasajero a la lista de la reserva.
        reservation.setItinerary(itinerary); // Asocia el itinerario a la reserva.

        // Agrega la reserva a la lista de reservas.
        reservations.add(reservation);
    }

    // Método para obtener todas las reservas.
    public List<Reservation> getReservations() {
        return reservations; // Devuelve la lista de reservas.
    }

    // Método para obtener una reserva por su ID.
    public Optional<Reservation> getReservationById(Long id) {
        // Filtra las reservas para encontrar la que coincide con el ID proporcionado.
        List<Reservation> result = reservations.stream()
                .filter(reservation -> Objects.equals(reservation.getId(), id))
                .toList();

        // Obtiene la primera reserva del resultado, si existe, y la devuelve como un Optional.
        Reservation reservation = !result.isEmpty() ? result.get(0) : null;
        return Optional.ofNullable(reservation);
    }

    // Método para guardar una nueva reserva.
    public Reservation save(Reservation reservation) {
        // Establece un nuevo ID para la reserva, basado en el tamaño actual de la lista.
        reservation.setId((long) (reservations.size() + 1));
        reservations.add(reservation); // Agrega la reserva a la lista.
        return reservation; // Devuelve la reserva guardada.
    }

    // Método para actualizar una reserva existente.
    public Reservation update(Long id, Reservation reservation) {
        // Filtra las reservas para encontrar la que coincide con el ID proporcionado.
        List<Reservation> result = reservations.stream()
                .filter(reser -> reser.getId().equals(id))
                .toList();

        // Actualiza los atributos de la reserva encontrada con los nuevos valores.
        result.get(0).setId(reservation.getId());
        result.get(0).setItinerary(reservation.getItinerary());
        result.get(0).setPassengers(reservation.getPassengers());

        return result.get(0); // Devuelve la reserva actualizada.
    }

    // Método para eliminar una reserva por su ID.
    public void delete(Long id) {
        // Filtra las reservas para encontrar la que coincide con el ID proporcionado.
        List<Reservation> result = reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .toList();

        // Elimina la reserva encontrada de la lista.
        reservations.remove(result.get(0));
    }
}
