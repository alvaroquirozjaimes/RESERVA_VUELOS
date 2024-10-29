package ReservationsTest.repository; // Define el paquete donde se encuentra la clase de pruebas.

import org.junit.jupiter.api.*; // Importa todas las anotaciones de JUnit 5 para las pruebas.
import org.junit.jupiter.params.ParameterizedTest; // Importa la anotación para pruebas parametrizadas.
import org.junit.jupiter.params.provider.CsvFileSource; // Importa la fuente de datos CSV para pruebas.
import org.junit.jupiter.params.provider.CsvSource; // Importa la fuente de datos CSV para pruebas.
import org.junit.jupiter.params.provider.ValueSource; // Importa la fuente de datos de valores individuales para pruebas.
import org.slf4j.Logger; // Importa la interfaz Logger de SLF4J para registro de logs.
import org.slf4j.LoggerFactory; // Importa la clase LoggerFactory para crear instancias de Logger.
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;

import java.util.List; // Importa la clase List para trabajar con colecciones de reservas.
import java.util.Optional; // Importa la clase Optional, que representa un valor que puede estar presente o ausente.
import java.util.concurrent.TimeUnit; // Importa la clase TimeUnit para manejar unidades de tiempo.

import static ReservationsTest.util.ReservationUtil.getReservation; // Importa el método estático getReservation desde la clase de utilidades.
import static org.hamcrest.MatcherAssert.assertThat; // Importa el método estático assertThat para aserciones.
import static org.hamcrest.Matchers.*; // Importa los matchers de Hamcrest para comparaciones.
import static org.junit.jupiter.api.Assertions.*; // Importa todas las aserciones estáticas de JUnit 5.

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Indica que los métodos de prueba se ejecutarán en el orden de sus anotaciones @Test.
@Tags(@Tag("repository")) // Etiqueta la clase de prueba con "repository".
@DisplayName("Check the functionality of the repository") // Título descriptivo de la clase de prueba.
class ReservationRepositoryTest { // Clase que contiene las pruebas para ReservationRepository.

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRepositoryTest.class); // Crea un logger para la clase.

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

    @Order(2) // Define el orden de ejecución de la prueba.
    @Tag("success-case") // Etiqueta la prueba como un caso de éxito.
    @DisplayName("should return the information of all the reservations using external files") // Título descriptivo de la prueba.
    @ParameterizedTest // Indica que este método es una prueba parametrizada.
    @CsvFileSource(resources = "/save-repository.csv") // Indica que los datos se cargarán desde un archivo CSV.
    void save_should_return_the_information_using_external_file(String origin, String destination) {
        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio de reservas.

        // When
        Reservation reservation = repository.save(getReservation(null, origin, destination)); // Guarda una nueva reserva.

        // Then
        assertAll(() -> assertNotNull(reservation), // Verifica que la reserva no sea nula.
                () -> assertEquals(origin, reservation.getItinerary().getSegment().get(0).getOrigin()), // Verifica el origen.
                () -> assertEquals(destination, reservation.getItinerary().getSegment().get(0).getDestination())); // Verifica el destino.
    }

    @Order(2) // Define el orden de ejecución de la prueba.
    @Tag("success-case") // Etiqueta la prueba como un caso de éxito.
    @DisplayName("should return the information of all the reservations using CSV") // Título descriptivo de la prueba.
    @ParameterizedTest // Indica que este método es una prueba parametrizada.
    @CsvSource({ "MIA,AEP", "BUE,SCL", "BUE,MIA" }) // Datos de prueba como pares de origen y destino.
    void save_should_return_the_information_using_csv(String origin, String destination) {
        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio de reservas.

        // When
        Reservation reservation = repository.save(getReservation(null, origin, destination)); // Guarda una nueva reserva.

        // Then
        assertAll(() -> assertNotNull(reservation), // Verifica que la reserva no sea nula.
                () -> assertEquals(origin, reservation.getItinerary().getSegment().get(0).getOrigin()), // Verifica el origen.
                () -> assertEquals(destination, reservation.getItinerary().getSegment().get(0).getDestination())); // Verifica el destino.
    }

    @Order(2) // Define el orden de ejecución de la prueba.
    @Tag("success-case") // Etiqueta la prueba como un caso de éxito.
    @DisplayName("should return the information of all the reservations using parameters") // Título descriptivo de la prueba.
    @ParameterizedTest // Indica que este método es una prueba parametrizada.
    @ValueSource(strings = { "AEP", "MIA" }) // Datos de prueba como orígenes.
    void save_should_return_the_information_using_parameters(String origin) {
        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio de reservas.

        // When
        Reservation reservation = repository.save(getReservation(null, origin, "MIA")); // Guarda una nueva reserva.

        // Then
        assertAll(() -> assertNotNull(reservation), // Verifica que la reserva no sea nula.
                () -> assertEquals(origin, reservation.getItinerary().getSegment().get(0).getOrigin()), // Verifica el origen.
                () -> assertEquals("MIA", reservation.getItinerary().getSegment().get(0).getDestination())); // Verifica el destino.
    }

    @Order(1) // Define el orden de ejecución de la prueba.
    @Tag("success-case") // Etiqueta la prueba como un caso de éxito.
    @DisplayName("should return the information of all the reservations") // Título descriptivo de la prueba.
    @Test // Indica que este método es una prueba.
    void getReservations_should_return_the_information() {
        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio de reservas.
        repository.save(getReservation(null, "AEP", "MIA")); // Guarda una nueva reserva.

        // When
        List<Reservation> result = repository.getReservations(); // Obtiene todas las reservas.

        // Then
        assertAll(() -> assertNotNull(result), // Verifica que la lista de resultados no sea nula.
                () -> assertThat(result, hasSize(2)), // Verifica que haya 2 reservas en la lista.
                () -> assertThat(getReservation(2L, "AEP", "MIA"), in(result)), // Verifica que la reserva específica esté presente.
                () -> assertThat(result.get(0), hasProperty("id")), // Verifica que la primera reserva tenga una propiedad "id".
                () -> assertThat(result.get(0).getPassengers().get(0).getFirstName(), stringContainsInOrder("A", "s")), // Verifica que el nombre contenga "A" y "s".
                () -> assertThat(result.get(0).getPassengers().get(0).getFirstName(), matchesRegex("[a-zA-Z]+"))); // Verifica que el nombre coincida con una expresión regular.
    }

    @Tag("success-case") // Etiqueta la prueba como un caso de éxito.
    @DisplayName("should return the information of the reservation") // Título descriptivo de la prueba.
    @Test // Indica que este método es una prueba.
    void getReservation_should_return_the_information() throws InterruptedException {
        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio de reservas.

        // When
        Optional<Reservation> result = repository.getReservationById(1L); // Intenta obtener la reserva con ID 1.

        // Then
        assertAll(() -> assertNotNull(result), // Verifica que el resultado no sea nulo.
                () -> assertTrue(result.isPresent()), // Verifica que la reserva esté presente.
                () -> assertEquals(getReservation(1L, "EZE", "MIA"), result.get())); // Verifica que el resultado coincida con la reserva esperada.
    }

    @Disabled // Indica que esta prueba está deshabilitada y no se ejecutará.
    @Tag("error-case") // Etiqueta la prueba como un caso de error.
    @DisplayName("should not return the information of the reservation by timeout problem") // Título descriptivo de la prueba.
    @Test // Indica que este método es una prueba.
    @Timeout(value = 1L, unit = TimeUnit.MILLISECONDS) // Indica que la prueba debe completarse en 1 milisegundo.
    void getReservation_should_not_return_the_information_by_timeout() throws InterruptedException {
        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio de reservas.

        // When
        Optional<Reservation> result = repository.getReservationById(1L); // Intenta obtener la reserva con ID 1.

        // Then
        assertAll(() -> assertNotNull(result), // Verifica que el resultado no sea nulo.
                () -> assertTrue(result.isPresent()), // Verifica que la reserva esté presente.
                () -> assertEquals(getReservation(1L, "EZE", "MIA"), result.get())); // Verifica que el resultado coincida con la reserva esperada.
    }

    @Order(1) // Define el orden de ejecución de la prueba.
    @Tag("error-case") // Etiqueta la prueba como un caso de error.
    @DisplayName("should not return the information of the reservation") // Título descriptivo de la prueba.
    @Test // Indica que este método es una prueba.
    void getReservation_should_not_return_the_information() {
        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio de reservas.

        // When
        Optional<Reservation> result = repository.getReservationById(95L); // Intenta obtener una reserva que no existe (ID 95).

        // Then
        assertAll(() -> assertNotNull(result), // Verifica que el resultado no sea nulo.
                () -> assertTrue(result.isEmpty())); // Verifica que el resultado esté vacío.
    }
}
