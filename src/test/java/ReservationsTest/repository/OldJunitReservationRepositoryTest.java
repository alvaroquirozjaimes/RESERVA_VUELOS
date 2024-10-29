package ReservationsTest.repository; // Paquete donde se ubica la clase de pruebas del repositorio

import org.junit.*; // Importa todas las anotaciones de JUnit
import org.slf4j.Logger; // Importa el logger de SLF4J
import org.slf4j.LoggerFactory; // Importa la fábrica de loggers
import reservation.model.Reservation;
import reservation.repository.ReservationRepository;

import java.util.Optional; // Importa la clase Optional para manejar valores que pueden ser nulos

import static ReservationsTest.util.ReservationUtil.getReservation; // Importa el método getReservation desde la utilidad de reservas
import static org.junit.Assert.*; // Importa todas las aserciones de JUnit

public class OldJunitReservationRepositoryTest { // Clase de prueba

    private static final Logger LOGGER = LoggerFactory.getLogger(OldJunitReservationRepositoryTest.class); // Logger para registrar información

    @Before // Anotación que indica que este método se ejecutará antes de cada prueba
    public void initialize_each_test() {
        LOGGER.info("Initialize the context on each test"); // Registra la inicialización del contexto antes de cada prueba
    }

    @After // Anotación que indica que este método se ejecutará después de cada prueba
    public void destroy_each_test() {
        LOGGER.info("Destroy the context on each test"); // Registra la destrucción del contexto después de cada prueba
    }

    @BeforeClass // Anotación que indica que este método se ejecutará una vez antes de todas las pruebas
    public static void initialize_all_test() {
        LOGGER.info("Initialize the context on all test"); // Registra la inicialización del contexto antes de todas las pruebas
    }

    @AfterClass // Anotación que indica que este método se ejecutará una vez después de todas las pruebas
    public static void destroy_all_test() {
        LOGGER.info("Destroy the context on all test"); // Registra la destrucción del contexto después de todas las pruebas
    }

    @Test // Anotación que indica que este método es una prueba
    public void getReservation_should_return_the_information() throws InterruptedException {

        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio

        // When
        Optional<Reservation> result = repository.getReservationById(1L); // Intenta obtener la reserva con ID 1

        // Then
        assertNotNull(result); // Verifica que el resultado no sea nulo
        assertTrue(result.isPresent()); // Verifica que la reserva esté presente
        assertEquals(getReservation(1L, "EZE", "MIA"), result.get()); // Verifica que el resultado coincida con la reserva esperada
    }

    @Test // Anotación que indica que este método es una prueba
    public void getReservation_should_not_return_the_information() {

        // Given
        ReservationRepository repository = new ReservationRepository(); // Crea una instancia del repositorio

        // When
        Optional<Reservation> result = repository.getReservationById(95L); // Intenta obtener la reserva con un ID que no existe

        // Then
        assertNotNull(result); // Verifica que el resultado no sea nulo
        assertFalse(result.isPresent()); // Verifica que la reserva no esté presente
    }

}
