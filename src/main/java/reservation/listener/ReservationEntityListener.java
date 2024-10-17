package reservation.listener;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reservation.model.Reservation;

import java.time.LocalDate;

// Clase que escucha los eventos del ciclo de vida de la entidad Reservation
public class ReservationEntityListener {

    // Logger para registrar eventos
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationEntityListener.class);

    // Método que se ejecuta antes de que la entidad sea persistida en la base de datos
    @PrePersist
    public void prePersist(Reservation reservation) {
        // Establece la fecha de creación de la reserva al momento actual
        reservation.setCreationDate(LocalDate.now());
    }

    // Método que se ejecuta después de que la entidad ha sido persistida en la base de datos
    @PostPersist
    public void postPersist(Reservation reservation) {
        // Registra la reserva recién persistida para auditoría o monitoreo
        LOGGER.info("postPersist with this reservation {}", reservation);
    }

    // Método que se ejecuta después de que la entidad ha sido eliminada de la base de datos
    @PostRemove
    public void onPostRemove(Reservation reservation) {
        // Registra la eliminación de la reserva para rastrear acciones
        LOGGER.info("onPostRemove with this reservation {}", reservation);
    }

    // Método que se ejecuta después de que la entidad ha sido cargada desde la base de datos
    @PostLoad
    public void onPostLoad(Reservation reservation) {
        // Registra la carga de la reserva para seguimiento
        LOGGER.info("onPostLoad with this reservation {}", reservation);
    }
}
