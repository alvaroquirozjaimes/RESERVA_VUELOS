package reservation.controller.resource;// Paquete donde se encuentra el controlador

import jakarta.validation.Valid; // Importa la anotación para validar objetos
import jakarta.validation.constraints.Min; // Importa la anotación para validar que el valor sea mínimo
import org.Reservations.integration.resource.ReservationResource; // Importa la interfaz de recursos de reservas
import org.slf4j.Logger; // Importa la interfaz de Logger
import org.slf4j.LoggerFactory; // Importa la clase para crear instancias de Logger
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para inyección de dependencias
import org.springframework.http.HttpStatus; // Importa la clase para manejar estados HTTP
import org.springframework.http.ResponseEntity; // Importa la clase para encapsular la respuesta HTTP
import org.springframework.validation.annotation.Validated; // Importa la anotación para validación de clases
import org.springframework.web.bind.annotation.*; // Importa las anotaciones de Spring para manejar solicitudes web
import reservation.dto.ReservationDTO;
import reservation.service.ReservationService;

import java.util.List; // Importa la clase List para manejar colecciones de reservas

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/reservation") // Mapea las solicitudes a la ruta "/reservation"
@Validated // Activa la validación de parámetros en los métodos del controlador
public class ReservationController implements ReservationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class); // Crea un logger para registrar eventos
    private final ReservationService service; // Servicio para manejar la lógica de negocio de reservas

    @Autowired // Inyección de dependencias para el servicio de reservas
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping // Maneja solicitudes GET a "/reservation"
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        LOGGER.info("Obtain all the reservations"); // Registro de información
        List<ReservationDTO> response = service.getReservations(); // Obtiene todas las reservas
        return new ResponseEntity<>(response, HttpStatus.OK); // Devuelve la respuesta con estado 200 OK
    }

    @GetMapping("/{id}") // Maneja solicitudes GET a "/reservation/{id}"
    public ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable Long id) {
        LOGGER.info("Obtain information from a reservation with {}", id); // Registro de información
        ReservationDTO response = service.getReservationById(id); // Obtiene la reserva por ID
        return new ResponseEntity<>(response, HttpStatus.OK); // Devuelve la respuesta con estado 200 OK
    }

    @PostMapping // Maneja solicitudes POST a "/reservation"
    public ResponseEntity<ReservationDTO> save(@RequestBody @Valid ReservationDTO reservation) {
        LOGGER.info("Saving new reservation"); // Registro de información
        ReservationDTO response = service.save(reservation); // Guarda la nueva reserva
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Devuelve la respuesta con estado 201 CREATED
    }

    @PutMapping("/{id}") // Maneja solicitudes PUT a "/reservation/{id}"
    public ResponseEntity<ReservationDTO> update(@Min(1) @PathVariable Long id,
                                                 @RequestBody @Valid ReservationDTO reservation) {
        LOGGER.info("Updating a reservation with {}", id); // Registro de información
        ReservationDTO response = service.update(id, reservation); // Actualiza la reserva por ID

        return new ResponseEntity<>(response, HttpStatus.OK); // Devuelve la respuesta con estado 200 OK
    }

    @DeleteMapping("/{id}") // Maneja solicitudes DELETE a "/reservation/{id}"
    public ResponseEntity<Void> delete(@Min(1) @PathVariable Long id) {
        LOGGER.info("Deleting a reservation with {}", id); // Registro de información
        service.delete(id); // Elimina la reserva por ID
        return new ResponseEntity<>(HttpStatus.OK); // Devuelve la respuesta con estado 200 OK
    }
}
