package reservation.service;// Especifica el paquete al que pertenece la clase.


 // Importa los códigos de error para la API.

import org.slf4j.Logger; // Importa la interfaz Logger de SLF4J para el registro.
import org.slf4j.LoggerFactory; // Importa la clase LoggerFactory para crear instancias de Logger.
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para la inyección de dependencias.
import org.springframework.core.convert.ConversionService; // Importa el servicio de conversión de objetos.
import org.springframework.stereotype.Service; // Importa la anotación Service de Spring.
import reservation.connector.CatalogConnector;
import reservation.connector.response.CityDTO;
import reservation.dto.ReservationDTO;
import reservation.dto.SegmentDTO;
import reservation.enums.APIError;
import reservation.exception.PersonalException;
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;

import java.util.List; // Importa las listas de Java.
import java.util.Objects; // Importa la clase Objects para operaciones comunes.
import java.util.Optional; // Importa la clase Optional para manejar valores opcionales.

@Service // Marca esta clase como un servicio de Spring, permitiendo su inyección de dependencias.
public class ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class); // Crea un logger para esta clase.

    private ReservationRepository repository; // Repositorio para acceder a las reservas.
    private ConversionService conversionService; // Servicio para convertir entre diferentes tipos de objetos.
    private CatalogConnector catalogConnector; // Conector para acceder a la información del catálogo.

    // Constructor que se utiliza para la inyección de dependencias.
    @Autowired
    public ReservationService(ReservationRepository repository, ConversionService conversionService,
                              CatalogConnector catalogConnector) {
        this.repository = repository; // Inicializa el repositorio.
        this.conversionService = conversionService; // Inicializa el servicio de conversión.
        this.catalogConnector = catalogConnector; // Inicializa el conector del catálogo.
    }

    // Método para obtener todas las reservas.
    public List<ReservationDTO> getReservations() {
        return conversionService.convert(repository.getReservations(), List.class); // Convierte la lista de reservas a DTOs.
    }

    // Método para obtener una reserva por su ID.
    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.getReservationById(id); // Busca la reserva en el repositorio.
        if (result.isEmpty()) { // Si no se encuentra la reserva...
            LOGGER.debug("Not exist reservation with the id {}", id); // Registra un mensaje de depuración.
            throw new PersonalException(APIError.RESERVATION_NOT_FOUND); // Lanza una excepción si no se encuentra.
        }
        return conversionService.convert(result.get(), ReservationDTO.class); // Convierte la reserva a DTO y la devuelve.
    }

    // Método para guardar una nueva reserva.
    public ReservationDTO save(ReservationDTO reservation) {
        if (Objects.nonNull(reservation.getId())) { // Verifica si la reserva ya tiene un ID.
            throw new PersonalException(APIError.RESERVATION_WITH_SAME_ID); // Lanza una excepción si tiene un ID.
        }
        checkCity(reservation); // Verifica las ciudades de la reserva.

        Reservation transformed = conversionService.convert(reservation, Reservation.class); // Convierte el DTO a modelo.
        Reservation result = repository.save(Objects.requireNonNull(transformed)); // Guarda la reserva y obtiene el resultado.
        return conversionService.convert(result, ReservationDTO.class); // Convierte y devuelve la reserva guardada como DTO.
    }

    // Método para actualizar una reserva existente.
    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if (getReservationById(id) == null) { // Verifica si la reserva existe.
            LOGGER.debug("Not exist reservation with the id {}", id); // Registra un mensaje de depuración.
            throw new PersonalException(APIError.RESERVATION_NOT_FOUND); // Lanza una excepción si no se encuentra.
        }
        checkCity(reservation); // Verifica las ciudades de la reserva.
        Reservation transformed = conversionService.convert(reservation, Reservation.class); // Convierte el DTO a modelo.
        Reservation result = repository.update(id, Objects.requireNonNull(transformed)); // Actualiza la reserva y obtiene el resultado.
        return conversionService.convert(result, ReservationDTO.class); // Convierte y devuelve la reserva actualizada como DTO.
    }

    // Método para eliminar una reserva por su ID.
    public void delete(Long id) {
        if (getReservationById(id) == null) { // Verifica si la reserva existe.
            LOGGER.debug("Not exist reservation with the id {}", id); // Registra un mensaje de depuración.
            throw new PersonalException(APIError.RESERVATION_NOT_FOUND); // Lanza una excepción si no se encuentra.
        }

        repository.delete(id); // Elimina la reserva del repositorio.
    }

    // Método privado para verificar si las ciudades en la reserva son válidas.
    private void checkCity(ReservationDTO reservationDTO) {
        // Itera sobre los segmentos de la reserva.
        for (SegmentDTO segmentDTO : reservationDTO.getItinerary().getSegment()) {
            // Obtiene la ciudad de origen y destino usando el conector del catálogo.
            CityDTO origin = catalogConnector.getCity(segmentDTO.getOrigin());
            CityDTO destination = catalogConnector.getCity(segmentDTO.getDestination());

            // Verifica si alguna de las ciudades es nula.
            if (origin == null || destination == null) {
                throw new PersonalException(APIError.VALIDATION_ERROR); // Lanza una excepción de validación si alguna ciudad no es válida.
            } else {
                // Registra los nombres de las ciudades para depuración.
                LOGGER.debug(origin.getName());
                LOGGER.debug(destination.getName());
            }
        }
    }
}
