package reservation.dao;

import jakarta.persistence.EntityManager; // Importa la clase para gestionar entidades
import jakarta.persistence.LockModeType; // Importa la enumeración para tipos de bloqueo
import jakarta.persistence.PersistenceContext; // Importa la anotación para inyección del contexto de persistencia
import jakarta.persistence.criteria.CriteriaBuilder; // Importa la clase para construir consultas
import jakarta.persistence.criteria.CriteriaQuery; // Importa la clase para definir consultas
import jakarta.persistence.criteria.Predicate; // Importa la clase para crear condiciones de consulta
import jakarta.persistence.criteria.Root; // Importa la clase que representa la raíz de la consulta
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para la inyección de dependencias
import org.springframework.data.jpa.repository.Lock; // Importa la anotación para manejar bloqueos en repositorios
import org.springframework.stereotype.Repository; // Importa la anotación para indicar que es un repositorio
import org.springframework.transaction.annotation.Transactional; // Importa la anotación para manejar transacciones
import org.springframework.transaction.support.TransactionTemplate; // Importa la clase para manejar transacciones
import reservation.dao.ReservationDao;
import reservation.dto.SearchReservationCriteriaDTO;
import reservation.model.Reservation;
import reservation.specification.ReservationSpecification;

import java.util.List; // Importa la clase List para manejar listas de reservas
import java.util.Objects; // Importa la clase Objects para operaciones en objetos
import java.util.Optional; // Importa la clase Optional para manejar valores opcionales

// Implementación de la interfaz ReservationDao
@Repository // Indica que esta clase es un repositorio
@Transactional // Habilita el manejo de transacciones en la clase
public class ReservationDaoImpl implements ReservationDao {

    @PersistenceContext // Inyecta el EntityManager para manejar la persistencia
    private EntityManager entityManager;

    @Autowired // Inyecta el TransactionTemplate para manejar transacciones
    private TransactionTemplate transactionTemplate;

    @Override
    public List<Reservation> findAll(SearchReservationCriteriaDTO criteria) {
        // Crea el CriteriaBuilder para construir la consulta
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Crea una nueva consulta para el tipo Reservation
        CriteriaQuery<Reservation> query = criteriaBuilder.createQuery(Reservation.class);
        // Define la raíz de la consulta, en este caso la entidad Reservation
        Root<Reservation> root = query.from(Reservation.class);

        // Obtiene un predicate basado en los criterios de búsqueda
        Predicate predicate = ReservationSpecification.withSearchCriteria(criteria).toPredicate(root, query, criteriaBuilder);
        // Establece las condiciones de la consulta
        query.where(predicate);

        // Ejecuta la consulta y devuelve la lista de reservas
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    // Descomentar para usar bloqueo pesimista
    //@Lock(LockModeType.PESSIMISTIC_READ)
    public Optional<Reservation> findById(Long id) {
        // Busca una reserva por su ID
        Reservation reservation = entityManager.find(Reservation.class, id);
        // Descomentar para usar bloqueo pesimista
        // Reservation reservation = entityManager.find(Reservation.class, id, LockModeType.PESSIMISTIC_READ);
        return Optional.ofNullable(reservation); // Devuelve un Optional con la reserva encontrada o vacío
    }

    @Override
    public Reservation save(Reservation reservation) {
        // Ejecuta la lógica de transacción
        transactionTemplate.execute(status -> {
            try {
                // Verifica si la reserva ya tiene un ID (existente)
                if (reservation.getId() != null) {
                    // Si existe, fusiona la reserva con los datos actuales
                    entityManager.merge(reservation);
                } else {
                    // Si no existe, persiste la nueva reserva
                    entityManager.persist(reservation);
                }
                // Asegura que los cambios se apliquen inmediatamente
                entityManager.flush();
                // Descomentar para simular una excepción y probar el rollback
                // throw new EdteamException(APIError.BAD_FORMAT);
            } catch (Exception e) {
                status.setRollbackOnly(); // Marca la transacción para rollback en caso de error
                throw e; // Vuelve a lanzar la excepción para que el rollback ocurra
            }
            return null; // Se requiere un valor de retorno para TransactionTemplate
        });

        return reservation; // Devuelve la reserva guardada
    }

    @Override
    public void deleteById(Long id) {
        // Busca la reserva por su ID
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) { // Verifica si la reserva existe
            // Elimina la reserva de la base de datos
            entityManager.remove(reservation);
            entityManager.flush(); // Asegura que los cambios se apliquen
        }
    }

    @Override
    public boolean existsById(Long id) {
        // Busca la reserva por su ID
        Reservation reservation = entityManager.find(Reservation.class, id);
        // Devuelve verdadero si la reserva existe, falso de lo contrario
        return Objects.nonNull(reservation);
    }
}
