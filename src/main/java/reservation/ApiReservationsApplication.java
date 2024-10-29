package reservation;// Especifica el paquete al que pertenece la clase.

import org.springframework.boot.SpringApplication; // Importa la clase SpringApplication para arrancar la aplicación Spring Boot.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación SpringBootApplication.

@SpringBootApplication // Esta anotación indica que es una clase de configuración de Spring Boot.
public class ApiReservationsApplication {

    // Método principal que se ejecuta al iniciar la aplicación.
    public static void main(String[] args) {
        // Llama al método run de SpringApplication para arrancar la aplicación.
        SpringApplication.run(ApiReservationsApplication.class, args);
    }
}
