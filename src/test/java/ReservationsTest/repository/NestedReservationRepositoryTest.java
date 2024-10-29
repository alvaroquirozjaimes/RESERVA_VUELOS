package ReservationsTest.repository; // Paquete donde se ubica la clase de pruebas del repositorio

import org.junit.jupiter.api.*; // Importa anotaciones de JUnit para las pruebas
import org.junit.jupiter.params.ParameterizedTest; // Importa para pruebas parametrizadas
import org.junit.jupiter.params.provider.CsvFileSource; // Importa para proporcionar datos desde un archivo CSV
import org.junit.jupiter.params.provider.CsvSource; // Importa para proporcionar datos directamente en la anotación
import org.junit.jupiter.params.provider.ValueSource; // Importa para proporcionar valores simples
import org.slf4j.Logger; // Importa el logger de SLF4J
import org.slf4j.LoggerFactory; // Importa la fábrica de loggers
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;

import java.util.Optional; // Importa la clase Optional para manejar valores que pueden ser nulos

import static ReservationsTest.util.ReservationUtil.getReservation; // Importa el método getReservation desde la utilidad de reservas
import static org.junit.jupiter.api.Assertions.*; // Importa todas las aserciones de JUnit

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Ordena los métodos de prueba por anotaciones @Order
@Tags(@Tag("repository")) // Marca la clase de prueba como una prueba de repositorio
@DisplayName("Check the functionality of the repository") // Nombre descriptivo de la clase de prueba
class NestedReservationRepositoryTest { // Clase de prueba

    private static final Logger LOGGER = LoggerFactory.getLogger(NestedReservationRepositoryTest.class); // Logger para registrar información

    @BeforeEach // Anotación que indica que este método se ejecutará antes de cada prueba
    void initialize_each_test() {
        LOGGER.info("Initialize the context on each test"); // Registra la inicialización del contexto antes de cada prueba
    }

    @AfterEach // Anotación que indica que este método se ejecutará después de cada prueba
    void destroy_each_test() {
        LOGGER.info("Destroy the context on each test"); // Registra la destrucción del contexto después de cada prueba
    }

    @BeforeAll // Anotación que indica que este método se ejecutará una vez antes de todas las pruebas
    static void initialize_all_test() {
        LOGGER.info("Initialize the context on all test"); // Registra la inicialización del contexto antes de todas las pruebas
    }

    @AfterAll // Anotación que indica que este método se ejecutará una vez después de todas las pruebas
    static void destroy_all_test() {
        LOGGER.info("Destroy the context on all test"); // Registra la destrucción del contexto después de todas las pruebas
    }

    // Clase anidada para pruebas de obtención de reservas
    @Nested
    class GetReservation {
        @Tag("success-case") // Marca esta prueba como un caso de éxito
        @DisplayName("should return the information of the reservation") // Nombre descriptivo de la prueba
        @Test // Anotación que indica que este método es una prueba
        void getReservation_should_return_the_information() throws InterruptedException {

            // Given
            ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio

            // When
            Optional<Reservation> result = repository.getReservationById(1L); // Intenta obtener la reserva con ID 1

            // Then
            assertAll(() -> assertNotNull(result), () -> assertTrue(result.isPresent()), // Verifica que el resultado no sea nulo y esté presente
                    () -> assertEquals(getReservation(1L, "EZE", "MIA"), result.get())); // Verifica que el resultado coincida con la reserva esperada
        }

        @Tag("error-case") // Marca esta prueba como un caso de error
        @DisplayName("should not return the information of the reservation") // Nombre descriptivo de la prueba
        @Test // Anotación que indica que este método es una prueba
        void getReservation_should_not_return_the_information() {

            // Given
            ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio

            // When
            Optional<Reservation> result = repository.getReservationById(50L); // Intenta obtener la reserva con un ID que no existe

            // Then
            assertAll(() -> assertNotNull(result), () -> assertTrue(result.isEmpty())); // Verifica que el resultado no sea nulo y esté vacío
        }
    }

    // Clase anidada para pruebas de guardado de reservas
    @Nested
    class SaveReservation {

        @Tag("success-case") // Marca esta prueba como un caso de éxito
        @DisplayName("should return the information of all the reservations using external files") // Nombre descriptivo de la prueba
        @ParameterizedTest // Indica que este método es una prueba parametrizada
        @CsvFileSource(resources = "/save-repository.csv") // Proporciona datos desde un archivo CSV
        void save_should_return_the_information_using_external_file(String origin, String destination) {

            // Given
            ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio

            // When
            Reservation reservation = repository.save(getReservation(null, origin, destination)); // Guarda una nueva reserva usando los datos proporcionados

            // Then
            assertAll(() -> assertNotNull(reservation), // Verifica que la reserva no sea nula
                    () -> assertEquals(origin, reservation.getItinerary().getSegment().get(0).getOrigin()), // Verifica que el origen sea correcto
                    () -> assertEquals(destination, reservation.getItinerary().getSegment().get(0).getDestination())); // Verifica que el destino sea correcto
        }

        @Tag("success-case") // Marca esta prueba como un caso de éxito
        @DisplayName("should return the information of all the reservations using CSV") // Nombre descriptivo de la prueba
        @ParameterizedTest // Indica que este método es una prueba parametrizada
        @CsvSource({ "MIA,AEP", "BUE,SCL", "BUE,MIA" }) // Proporciona datos directamente en la anotación
        void save_should_return_the_information_using_csv(String origin, String destination) {

            // Given
            ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio

            // When
            Reservation reservation = repository.save(getReservation(null, origin, destination)); // Guarda una nueva reserva usando los datos proporcionados

            // Then
            assertAll(() -> assertNotNull(reservation), // Verifica que la reserva no sea nula
                    () -> assertEquals(origin, reservation.getItinerary().getSegment().get(0).getOrigin()), // Verifica que el origen sea correcto
                    () -> assertEquals(destination, reservation.getItinerary().getSegment().get(0).getDestination())); // Verifica que el destino sea correcto
        }

        @Tag("success-case") // Marca esta prueba como un caso de éxito
        @DisplayName("should return the information of all the reservations using parameters") // Nombre descriptivo de la prueba
        @ParameterizedTest // Indica que este método es una prueba parametrizada
        @ValueSource(strings = { "AEP", "MIA" }) // Proporciona valores simples para la prueba
        void save_should_return_the_information_using_parameters(String origin) {

            // Given
            ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio

            // When
            Reservation reservation = repository.save(getReservation(null, origin, "MIA")); // Guarda una nueva reserva usando los datos proporcionados

            // Then
            assertAll(() -> assertNotNull(reservation), // Verifica que la reserva no sea nula
                    () -> assertEquals(origin, reservation.getItinerary().getSegment().get(0).getOrigin()), // Verifica que el origen sea correcto
                    () -> assertEquals("MIA", reservation.getItinerary().getSegment().get(0).getDestination())); // Verifica que el destino sea correcto
        }
    }

}
