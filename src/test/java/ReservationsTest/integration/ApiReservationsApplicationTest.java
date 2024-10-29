package ReservationsTest.integration; // Paquete donde se ubica la clase de pruebas de integración

import org.junit.jupiter.api.*; // Importa anotaciones de JUnit para las pruebas
import org.springframework.boot.test.context.SpringBootTest; // Importa la anotación para cargar el contexto de Spring
import org.springframework.test.web.servlet.MockMvc; // Importa la clase MockMvc para simular peticiones HTTP
import org.springframework.test.web.servlet.setup.MockMvcBuilders; // Importa la clase para construir MockMvc
import org.springframework.web.context.WebApplicationContext; // Importa el contexto de la aplicación web

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // Importa el método para realizar solicitudes GET
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Importa los métodos para verificar resultados de estado

@Tags(@Tag("integration")) // Marca la clase de prueba como una prueba de integración
@DisplayName("Check the functionality of the application") // Nombre descriptivo de la clase de prueba
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Indica que se debe iniciar un contexto de Spring Boot con un puerto aleatorio
class ApiReservationsApplicationTest { // Clase de prueba

    private MockMvc mockMvc; // Variable para almacenar el objeto MockMvc

    @BeforeEach // Anotación que indica que este método se ejecutará antes de cada prueba
    public void setUp(WebApplicationContext webApplicationContext) { // Método de configuración
        // Configura MockMvc para usar el contexto de la aplicación web
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Tag("success-case") // Marca esta prueba como un caso de éxito
    @DisplayName("should return the information of the reservation") // Nombre descriptivo de la prueba
    @Test // Anotación que indica que este método es una prueba
    void should_get_a_reservation() throws Exception { // Método de prueba
        // Simula una solicitud GET a la URL "/reservation/1" y espera un estado 2xx (éxito)
        mockMvc.perform(get("/reservation/1").contentType("application/json"))
                .andExpect(status().is2xxSuccessful());
    }

    @Tag("error-case") // Marca esta prueba como un caso de error
    @DisplayName("should not return the information of the reservation") // Nombre descriptivo de la prueba
    @Test // Anotación que indica que este método es una prueba
    void should_not_get_a_reservation() throws Exception { // Método de prueba
        // Simula una solicitud GET a la URL "/reservation/100" y espera un estado 404 (no encontrado)
        mockMvc.perform(get("/reservation/100").contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}
