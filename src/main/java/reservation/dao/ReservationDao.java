package reservation.dao;

import reservation.dto.SearchReservationCriteriaDTO;
import reservation.model.Reservation;

import java.util.List; // Importa la clase List para manejar listas de reservas
import java.util.Optional; // Importa la clase Optional para manejar valores opcionales

// Interfaz que define las operaciones de acceso a datos para las reservas
public interface ReservationDao {

    // Método para encontrar todas las reservas que coinciden con los criterios de búsqueda
    List<Reservation> findAll(SearchReservationCriteriaDTO criteria);

    // Método para buscar una reserva por su ID
    Optional<Reservation> findById(Long id);

    // Método para guardar una nueva reserva o actualizar una existente
    Reservation save(Reservation reservation);

    // Método para eliminar una reserva por su ID
    void deleteById(Long id);

    // Método para verificar si una reserva existe por su ID
    boolean existsById(Long id);
}
