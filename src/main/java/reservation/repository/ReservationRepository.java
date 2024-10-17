package reservation.repository;

import org.springframework.data.domain.Pageable;  // Importa la interfaz para la paginación
import org.springframework.data.jpa.domain.Specification;  // Importa la interfaz Specification para criterios de búsqueda
import org.springframework.data.jpa.repository.JpaRepository;  // Importa la interfaz JpaRepository para operaciones CRUD
import org.springframework.data.jpa.repository.Query;  // Importa la anotación Query para consultas personalizadas
import org.springframework.data.repository.query.Param;  // Importa la anotación Param para parámetros de consulta
import org.springframework.transaction.annotation.Transactional;  // Importa la anotación para la gestión de transacciones
import reservation.model.Reservation;

import java.time.LocalDate;  // Importa la clase LocalDate para manejar fechas
import java.util.List;  // Importa la clase List para manejar listas

// Interfaz que extiende JpaRepository para la entidad Reservation
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Consultas definidas como constantes
    String QUERY_FIND_BY_CREATION_DATE = "SELECT r FROM Reservation r WHERE r.creationDate = :creationDate";
    String QUERY_FIND_BY_CREATION_DATE_AND_FIRSTNAME = "SELECT r FROM Reservation r JOIN r.passengers p WHERE r.creationDate = :creationDate AND p.firstName = :firstName";
    String QUERY_FIND_BY_CREATION_DATE_AND_FIRSTNAME_AND_LASTNAME = "SELECT r FROM Reservation r JOIN r.passengers p WHERE r.creationDate = :creationDate AND p.firstName = :firstName AND p.lastName = :lastName";

    // Consulta para encontrar reservas por fecha de creación
    @Query(value = QUERY_FIND_BY_CREATION_DATE)
    List<Reservation> findByCreationDate(@Param("creationDate") LocalDate creationDate);

    // Consulta para encontrar reservas por fecha de creación y nombre de pasajero
    @Query(QUERY_FIND_BY_CREATION_DATE_AND_FIRSTNAME)
    List<Reservation> findByCreationDateAndPassengersFirstName(@Param("creationDate") LocalDate creationDate,
                                                               @Param("firstName") String firstName);

    // Consulta para encontrar reservas por fecha de creación, nombre y apellido de pasajero
    @Query(QUERY_FIND_BY_CREATION_DATE_AND_FIRSTNAME_AND_LASTNAME)
    List<Reservation> findByCreationDateAndPassengersFirstNameAndPassengersLastName(
            @Param("creationDate") LocalDate creationDate, @Param("firstName") String firstName,
            @Param("lastName") String lastName);

    // Método para encontrar todas las reservas usando una especificación y paginación
    @Transactional(readOnly = true, timeout = 30)  // Configura la transacción como de solo lectura
    List<Reservation> findAll(Specification<Reservation> specification, Pageable pageable);

    // Método para obtener todas las reservas ordenadas por fecha de creación en orden descendente
    List<Reservation> findAllByOrderByCreationDateDesc();
}
