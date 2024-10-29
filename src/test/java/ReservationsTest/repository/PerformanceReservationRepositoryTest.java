package ReservationsTest.repository; // Define el paquete donde se encuentra la clase de pruebas.


import org.junit.jupiter.api.*; // Importa todas las anotaciones de JUnit 5 para las pruebas.
import org.quickperf.annotation.ExpectMaxExecutionTime; // Importa la anotación para establecer un tiempo máximo de ejecución esperado.
import org.quickperf.junit5.QuickPerfTest; // Importa la clase de prueba para QuickPerf.
import org.quickperf.jvm.annotations.MeasureHeapAllocation; // Importa la anotación para medir la asignación de memoria en el heap.
import org.slf4j.Logger; // Importa la interfaz Logger de SLF4J para registro de logs.
import org.slf4j.LoggerFactory; // Importa la clase LoggerFactory para crear instancias de Logger.
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;

// Importa la clase Optional, que representa un valor que puede estar presente o ausente.

import java.util.Optional;

import static ReservationsTest.util.ReservationUtil.getReservation; // Importa el método estático getReservation desde la clase de utilidades.
import static org.junit.jupiter.api.Assertions.*; // Importa todas las aserciones estáticas de JUnit 5.

@QuickPerfTest // Anotación que indica que esta es una clase de prueba de rendimiento.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Indica que los métodos de prueba se ejecutarán en el orden de sus anotaciones @Test.
@Tags(@Tag("performance")) // Etiqueta la clase de prueba con "performance".
@DisplayName("Check the functionality of the repository") // Título descriptivo de la clase de prueba.
class PerformanceReservationRepositoryTest { // Clase que contiene las pruebas de rendimiento para ReservationRepository.

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceReservationRepositoryTest.class); // Crea un logger para la clase.

    @BeforeEach // Indica que este método se ejecutará antes de cada prueba.
    void initialize_each_test() {
        LOGGER.info("Initialize the context on each test"); // Registra un mensaje antes de cada prueba.
    }

    @AfterEach // Indica que este método se ejecutará después de cada prueba.
    void destroy_each_test() {
        LOGGER.info("Destroy the context on each test"); // Registra un mensaje después de cada prueba.
    }

    @BeforeAll // Indica que este método se ejecutará una vez antes de todas las pruebas.
    static void initialize_all_test() {
        LOGGER.info("Initialize the context on all test"); // Registra un mensaje antes de ejecutar todas las pruebas.
    }

    @AfterAll // Indica que este método se ejecutará una vez después de todas las pruebas.
    static void destroy_all_test() {
        LOGGER.info("Destroy the context on all test"); // Registra un mensaje después de ejecutar todas las pruebas.
    }

    @MeasureHeapAllocation // Indica que se medirá la asignación de memoria en el heap durante esta prueba.
    @ExpectMaxExecutionTime(seconds = 2) // Define que el tiempo máximo de ejecución de la prueba debe ser de 2 segundos.
    @Tag("success-case") // Etiqueta la prueba como un caso de éxito.
    @DisplayName("should return the information of the reservation with heap") // Título descriptivo de la prueba.
    @Test // Indica que este método es una prueba.
    void getReservation_should_return_the_information_with_heap() {

        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio de reservas.

        // When
        Optional<Reservation> result = repository.getReservationById(1L); // Intenta obtener la reserva con ID 1.

        // Then
        assertAll(() -> assertNotNull(result), // Verifica que el resultado no sea nulo.
                () -> assertTrue(result.isPresent()), // Verifica que la reserva esté presente.
                () -> assertEquals(getReservation(1L, "EZE", "MIA"), result.get())); // Verifica que el resultado coincida con la reserva esperada.
    }
}
