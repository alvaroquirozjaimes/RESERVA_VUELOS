package ReservationsTest.service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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

@Tags(@Tag("service")) // Etiqueta para identificar la clase de prueba.
@DisplayName("Check the functionality of the service") // Descripción de la clase de prueba.
class SpyReservationServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpyReservationServiceTest.class); // Logger para registrar información.

    @Spy // Usar spy para el repositorio, permitiendo invocar métodos reales.
    ReservationRepository repository;

    @Mock // Simula el servicio de conversión.
    ConversionService conversionService;

    @Mock // Simula el conector del catálogo.
    CatalogConnector catalogConnector;

    @BeforeEach // Este método se ejecuta antes de cada prueba.
    void initialize_each_test() {
        LOGGER.info("Initialize the context on each test"); // Registra un mensaje al iniciar cada prueba.
        MockitoAnnotations.openMocks(this); // Inicializa los mocks.
    }

    @Tag("error-case") // Etiqueta para los casos de error.
    @DisplayName("should not return the information of the reservation") // Descripción de la prueba.
    @Test // Indica que este método es una prueba.
    void getReservation_should_not_return_the_information() {

        // Given
        ReservationService service = new ReservationService(repository, conversionService, catalogConnector); // Crea la instancia del servicio.
        when(repository.getReservationById(6L)).thenReturn(Optional.empty()); // Simula que no se encuentra la reserva.

        // When
        PersonalException exception = assertThrows(PersonalException.class, () -> {
            service.getReservationById(6L); // Intenta obtener la reserva.
        });

        // Then
        verify(repository, Mockito.atMostOnce()).getReservationById(6L); // Verifica que se llamó al método del repositorio una vez como máximo.

        assertAll(() -> assertNotNull(exception), // Verifica que la excepción no sea nula.
                () -> assertEquals(APIError.RESERVATION_NOT_FOUND.getMessage(), exception.getDescription()), // Verifica el mensaje de error.
                () -> assertEquals(APIError.RESERVATION_NOT_FOUND.getHttpStatus(), exception.getStatus())); // Verifica el estado HTTP.
    }

    @Tag("success-case") // Etiqueta para los casos de éxito.
    @DisplayName("should return the information of the reservation") // Descripción de la prueba.
    @Test // Indica que este método es una prueba.
    void getReservation_should_return_the_information() {

        // Given
        ReservationService service = new ReservationService(repository, conversionService, catalogConnector); // Crea la instancia del servicio.

        Reservation reservationModel = getReservation(1L, "EZE", "MIA"); // Crea un modelo de reserva.

        ReservationDTO reservationDTO = getReservationDTO(1L, "EZE", "MIA"); // Crea un DTO de reserva.
        when(conversionService.convert(reservationModel, ReservationDTO.class)).thenReturn(reservationDTO); // Simula la conversión.

        // When
        ReservationDTO result = service.getReservationById(1L); // Obtiene la reserva.

        // Then
        verify(repository, Mockito.atMostOnce()).getReservationById(1L); // Verifica que se llamó al método del repositorio una vez como máximo.
        verify(conversionService, Mockito.atMostOnce()).convert(reservationModel, ReservationDTO.class); // Verifica que la conversión fue llamada.
        verify(catalogConnector, Mockito.never()).getCity(any()); // Verifica que no se llamó al conector del catálogo.

        assertAll(() -> assertNotNull(result), // Verifica que el resultado no sea nulo.
                () -> assertEquals(getReservationDTO(1L, "EZE", "MIA"), result)); // Verifica que el resultado sea igual al DTO esperado.
    }
}
