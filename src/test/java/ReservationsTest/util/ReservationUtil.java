package ReservationsTest.util;

import reservation.dto.*;
import reservation.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReservationUtil {

    public static ReservationDTO getReservationDTO(Long id, String origin, String destination) {
        // Crea un objeto PassengerDTO con datos de ejemplo
        PassengerDTO passenger = new PassengerDTO();
        passenger.setFirstName("Alvaro");
        passenger.setLastName("Quiroz");
        passenger.setDocumentType("DNI");
        passenger.setDocumentNumber("12345678");
        passenger.setBirthday(LocalDate.of(1985, 1, 1));

        // Crea un objeto PriceDTO con precios de ejemplo
        PriceDTO price = new PriceDTO();
        price.setBasePrice(BigDecimal.ONE);
        price.setTotalTax(BigDecimal.ZERO);
        price.setTotalPrice(BigDecimal.ONE);

        // Crea un objeto SegmentDTO con información del segmento
        SegmentDTO segment = new SegmentDTO();
        segment.setArrival("2025-01-01");
        segment.setDeparture("2024-12-31");
        segment.setOrigin(origin);
        segment.setDestination(destination);
        segment.setCarrier("AA");

        // Crea un objeto ItineraryDTO con la información del itinerario
        ItineraryDTO itinerary = new ItineraryDTO();
        itinerary.setPrice(price);
        itinerary.setSegment(List.of(segment));

        // Crea un objeto ReservationDTO que agrupa todo
        ReservationDTO reservation = new ReservationDTO();
        reservation.setId(id);
        reservation.setPassengers(List.of(passenger));
        reservation.setItinerary(itinerary);

        return reservation; // Retorna el objeto ReservationDTO
    }

    public static Reservation getReservation(Long id, String origin, String destination) {
        // Crea un objeto Passenger con datos de ejemplo
        Passenger passenger = new Passenger();
        passenger.setFirstName("Alvaro");
        passenger.setLastName("Quiroz");
        passenger.setId(1L);
        passenger.setDocumentType("DNI");
        passenger.setDocumentNumber("12345678");
        passenger.setBirthday(LocalDate.of(1985, 1, 1));

        // Crea un objeto Price con precios de ejemplo
        Price price = new Price();
        price.setBasePrice(BigDecimal.ONE);
        price.setTotalTax(BigDecimal.ZERO);
        price.setTotalPrice(BigDecimal.ONE);

        // Crea un objeto Segment con información del segmento
        Segment segment = new Segment();
        segment.setArrival("2025-01-01");
        segment.setDeparture("2024-12-31");
        segment.setOrigin(origin);
        segment.setDestination(destination);
        segment.setCarrier("AA");
        segment.setId(1L);

        // Crea un objeto Itinerary con la información del itinerario
        Itinerary itinerary = new Itinerary();
        itinerary.setId(1L);
        itinerary.setPrice(price);
        itinerary.setSegment(List.of(segment));

        // Crea un objeto Reservation que agrupa todo
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setPassengers(List.of(passenger));
        reservation.setItinerary(itinerary);

        return reservation; // Retorna el objeto Reservation
    }
}
