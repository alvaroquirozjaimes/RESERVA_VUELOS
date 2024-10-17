package reservation.service;

import jakarta.validation.*;  // Importa clases para la validación
import org.slf4j.Logger;  // Importa la interfaz Logger para la gestión de logs
import org.slf4j.LoggerFactory;  // Importa la fábrica de logs
import org.springframework.beans.factory.annotation.Autowired;  // Importa la anotación para inyección de dependencias
import org.springframework.core.convert.ConversionService;  // Importa el servicio de conversión
import org.springframework.data.domain.PageRequest;  // Importa la clase para la creación de peticiones paginadas
import org.springframework.data.domain.Pageable;  // Importa la interfaz para la paginación
import org.springframework.stereotype.Service;  // Importa la anotación para definir un servicio
import reservation.dto.ReservationDTO;
import reservation.dto.SearchReservationCriteriaDTO;
import reservation.enums.APIError;
import reservation.exception.PersonalException;
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;
import reservation.specification.ReservationSpecification;

import java.util.List;  // Importa la clase List para manejar listas
import java.util.Objects;  // Importa la clase Objects para operaciones utilitarias
import java.util.Optional;  // Importa la clase Optional para manejar valores que pueden ser nulos
import java.util.Set;  // Importa la clase Set para trabajar con conjuntos

@Service  // Anota la clase como un servicio de Spring
public class ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);  // Logger para esta clase

    private final ReservationRepository repository;  // Repositorio para la gestión de reservas
    private final ConversionService conversionService;  // Servicio para la conversión entre tipos

    // Constructor de la clase con inyección de dependencias
    @Autowired
    public ReservationService(ReservationRepository repository, ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    // Método para obtener reservas según criterios de búsqueda
    public List<ReservationDTO> getReservations(SearchReservationCriteriaDTO criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageActual(), criteria.getPageSize());  // Crea un objeto Pageable para paginación
        // Retorna las reservas convertidas a DTO según los criterios de búsqueda
        return conversionService.convert(repository.findAll(ReservationSpecification.withSearchCriteria(criteria), pageable), List.class);
    }

    // Método para obtener una reserva por su ID
    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.findById(id);  // Busca la reserva por ID
        if (result.isEmpty()) {  // Si no se encuentra la reserva
            LOGGER.debug("Not exist reservation with the id {}", id);  // Registra un mensaje de depuración
            throw new PersonalException(APIError.RESERVATION_NOT_FOUND);  // Lanza una excepción si no se encuentra
        }
        return conversionService.convert(result.get(), ReservationDTO.class);  // Convierte y retorna el resultado
    }

    // Método para guardar una nueva reserva
    public ReservationDTO save(ReservationDTO reservation) {
        if (Objects.nonNull(reservation.getId())) {  // Si la reserva ya tiene un ID
            throw new PersonalException(APIError.RESERVATION_WITH_SAME_ID);  // Lanza una excepción
        }
        Reservation transformed = conversionService.convert(reservation, Reservation.class);  // Convierte el DTO a la entidad

        validateEntity(transformed);  // Valida la entidad transformada

        Reservation result = repository.save(Objects.requireNonNull(transformed));  // Guarda la reserva en el repositorio
        return conversionService.convert(result, ReservationDTO.class);  // Convierte y retorna el resultado
    }

    // Método para validar la entidad de la reserva
    private void validateEntity(Reservation transformed) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();  // Crea un factory de validación
        Validator validator = factory.getValidator();  // Obtiene un validador
        Set<ConstraintViolation<Reservation>> violations = validator.validate(transformed);  // Valida la entidad
        if (!violations.isEmpty()) {  // Si hay violaciones de las restricciones
            throw new ConstraintViolationException(violations);  // Lanza una excepción con las violaciones
        }
    }

    // Método para actualizar una reserva existente
    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if (!repository.existsById(id)) {  // Verifica si la reserva existe
            LOGGER.debug("Not exist reservation with the id {}", id);  // Registra un mensaje de depuración
            throw new PersonalException(APIError.RESERVATION_NOT_FOUND);  // Lanza una excepción si no se encuentra
        }
        LOGGER.info("Queries executes to obtain the information");  // Registra un mensaje de información

        Reservation transformed = conversionService.convert(reservation, Reservation.class);  // Convierte el DTO a la entidad
        validateEntity(transformed);  // Valida la entidad
        Reservation result = repository.save(Objects.requireNonNull(transformed));  // Guarda la reserva actualizada
        return conversionService.convert(result, ReservationDTO.class);  // Convierte y retorna el resultado
    }

    // Método para eliminar una reserva por su ID
    public void delete(Long id) {
        if (!repository.existsById(id)) {  // Verifica si la reserva existe
            LOGGER.debug("Not exist reservation with the id {}", id);  // Registra un mensaje de depuración
            throw new PersonalException(APIError.RESERVATION_NOT_FOUND);  // Lanza una excepción si no se encuentra
        }

        repository.deleteById(id);  // Elimina la reserva del repositorio
    }
}
