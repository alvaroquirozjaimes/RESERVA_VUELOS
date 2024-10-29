package ReservationsTest.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import reservation.connector.CatalogConnector;
import reservation.dto.ReservationDTO;
import reservation.enums.APIError;
import reservation.exception.PersonalException;
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;
import reservation.service.ReservationService;

import java.util.Optional;

import static ReservationsTest.util.ReservationUtil.getReservation;
import static ReservationsTest.util.ReservationUtil.getReservationDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tags(@Tag("parallel")) // Etiqueta la clase de prueba para que las pruebas se ejecuten en paralelo.
@DisplayName("Check the functionality of the service") // Título descriptivo de la clase de prueba.
class ParallelReservationServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParallelReservationServiceTest.class); // Crea un logger para la clase.

    @Mock // Indica que estas dependencias serán simuladas por Mockito.
    ReservationRepository repository;

    @Mock
    ConversionService conversionService;

    @Mock
    CatalogConnector catalogConnector;

    @BeforeEach // Este método se ejecuta antes de cada prueba.
    void initialize_each_test() {
        LOGGER.info("Initialize the context on each test"); // Registra un mensaje antes de cada prueba.
        MockitoAnnotations.openMocks(this); // Inicializa los mocks.
    }

    @Tag("error-case") // Etiqueta esta prueba como un caso de error.
    @DisplayName("should not return the information of the reservation") // Título descriptivo de la prueba.
    @Test // Indica que este método es una prueba.
    @ResourceLock(value = "reservation") // Bloquea el recurso "reservation" para evitar condiciones de carrera.
    void getReservation_should_not_return_the_information() throws InterruptedException {

        LOGGER.info("Running getReservation_should_not_return_the_information");

        // Given
        ReservationService service = new ReservationService(repository, conversionService, catalogConnector); // Crea una instancia del servicio.
        when(repository.getReservationById(6L)).thenReturn(Optional.empty()); // Simula la respuesta del repositorio.

        Thread.sleep(500); // Simula una pausa.

        // When
        PersonalException exception = assertThrows(PersonalException.class, () -> {
            service.getReservationById(6L); // Intenta obtener una reserva que no existe.
        });

        // Then
        verify(repository, Mockito.atMostOnce()).getReservationById(6L); // Verifica que el método del repositorio fue llamado una vez como máximo.

        assertAll(() -> assertNotNull(exception), // Verifica que la excepción no sea nula.
                () -> assertEquals(APIError.RESERVATION_NOT_FOUND.getMessage(), exception.getDescription()), // Verifica el mensaje de error.
                () -> assertEquals(APIError.RESERVATION_NOT_FOUND.getHttpStatus(), exception.getStatus())); // Verifica el estado HTTP.
    }

    @Tag("success-case") // Etiqueta esta prueba como un caso de éxito.
    @DisplayName("should return the information of the reservation") // Título descriptivo de la prueba.
    @Test // Indica que este método es una prueba.
    @ResourceLock(value = "reservation") // Bloquea el recurso "reservation" para evitar condiciones de carrera.
    void getReservation_should_return_the_information() throws InterruptedException {

        LOGGER.info("Running getReservation_should_return_the_information");

        // Given
        ReservationService service = new ReservationService(repository, conversionService, catalogConnector); // Crea una instancia del servicio.

        Reservation reservationModel = getReservation(1L, "BUE", "MAD"); // Crea un modelo de reserva.
        when(repository.getReservationById(1L)).thenReturn(Optional.of(reservationModel)); // Simula la respuesta del repositorio.

        ReservationDTO reservationDTO = getReservationDTO(1L, "BUE", "MAD"); // Crea un DTO de reserva.
        when(conversionService.convert(reservationModel, ReservationDTO.class)).thenReturn(reservationDTO); // Simula la conversión.

        Thread.sleep(500); // Simula una pausa.

        // When
        ReservationDTO result = service.getReservationById(1L); // Obtiene la reserva.

        // Then
        verify(repository, Mockito.atMostOnce()).getReservationById(1L); // Verifica que el método del repositorio fue llamado una vez como máximo.
        verify(conversionService, Mockito.atMostOnce()).convert(reservationModel, ReservationDTO.class); // Verifica que la conversión fue llamada.
        verify(catalogConnector, Mockito.never()).getCity(any()); // Verifica que no se haya llamado al conector del catálogo.

        assertAll(() -> assertNotNull(result), // Verifica que el resultado no sea nulo.
                () -> assertEquals(getReservationDTO(1L, "BUE", "MAD"), result)); // Verifica que el resultado sea igual al DTO esperado.
    }

    @Tag("success-case") // Etiqueta esta prueba como un caso de éxito.
    @DisplayName("should return remove a reservation") // Título descriptivo de la prueba.
    @Test // Indica que este método es una prueba.
    void delete_should_remove_a_reservation() throws InterruptedException {

        LOGGER.info("Running delete_should_remove_a_reservation");

        // Given
        ReservationService service = new ReservationService(repository, conversionService, catalogConnector); // Crea una instancia del servicio.

        Reservation reservationModel = getReservation(1L, "BUE", "MAD"); // Crea un modelo de reserva.
        when(repository.getReservationById(1L)).thenReturn(Optional.of(reservationModel)); // Simula la respuesta del repositorio.

        ReservationDTO reservationDTO = getReservationDTO(1L, "BUE", "MAD"); // Crea un DTO de reserva.
        when(conversionService.convert(reservationModel, ReservationDTO.class)).thenReturn(reservationDTO); // Simula la conversión.

        doNothing().when(repository).delete(1L); // Simula la eliminación sin hacer nada.

        Thread.sleep(500); // Simula una pausa.

        // When
        service.delete(1L); // Elimina la reserva.

        // Then
        verify(repository, Mockito.atMostOnce()).delete(1L); // Verifica que el método de eliminación fue llamado una vez como máximo.
        verify(repository, Mockito.atMostOnce()).getReservationById(1L); // Verifica que se haya verificado la existencia de la reserva.
        verify(conversionService, Mockito.atMostOnce()).convert(reservationModel, ReservationDTO.class); // Verifica que la conversión fue llamada.
        verify(catalogConnector, Mockito.never()).getCity(any()); // Verifica que no se haya llamado al conector del catálogo.
    }
}
