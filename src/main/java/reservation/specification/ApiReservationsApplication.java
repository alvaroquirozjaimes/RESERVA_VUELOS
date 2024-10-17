package reservation.specification;

import org.springframework.boot.SpringApplication;  // Importa la clase para ejecutar la aplicación Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;  // Importa la anotación para la configuración automática de Spring

// Anotación que indica que esta es una aplicación Spring Boot
@SpringBootApplication
public class ApiReservationsApplication {

    // Método principal que sirve como punto de entrada de la aplicación
    public static void main(String[] args) {
        // Llama al método run de SpringApplication para iniciar la aplicación
        SpringApplication.run(ApiReservationsApplication.class, args);
    }

}
