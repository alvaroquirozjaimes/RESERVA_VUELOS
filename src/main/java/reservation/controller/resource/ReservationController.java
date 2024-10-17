package reservation.controller.resource;

import org.slf4j.Logger; // Importa la clase para el registro de logs
import org.slf4j.LoggerFactory; // Importa la fábrica de logs
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para la inyección de dependencias
import org.springframework.http.HttpStatus; // Importa la clase para manejar estados HTTP
import org.springframework.http.ResponseEntity; // Importa la clase para construir respuestas HTTP
import org.springframework.validation.annotation.Validated; // Importa la anotación para validar datos
import org.springframework.web.bind.annotation.*; // Importa las anotaciones para manejar solicitudes web
import reservation.dto.ReservationDTO;
import reservation.dto.SearchReservationCriteriaDTO;
import reservation.service.ReservationService;

import java.util.List; // Importa la clase List para manejar colecciones

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/reservation") // Especifica la ruta base para las reservas
@Validated // Permite la validación de las entradas
public class ReservationController implements ReservationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class); // Inicializa el logger
    private final ReservationService service; // Declara el servicio de reservas

    @Autowired // Inyecta el servicio a través del constructor
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    // Método para obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations(SearchReservationCriteriaDTO criteria) {
        LOGGER.info("Obtain all the reservations"); // Registra la acción
        List<ReservationDTO> response = service.getReservations(criteria); // Llama al servicio para obtener reservas
        return new ResponseEntity<>(response, HttpStatus.OK); // Retorna la lista de reservas con estado OK
    }

    // Método para obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        LOGGER.info("Obtain information from a reservation with {}", id); // Registra la acción
        ReservationDTO response = service.getReservationById(id); // Llama al servicio para obtener la reserva
        return new ResponseEntity<>(response, HttpStatus.OK); // Retorna la reserva con estado OK
    }

    // Método para guardar una nueva reserva
    @PostMapping
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservation) {
        LOGGER.info("Saving new reservation"); // Registra la acción
        ReservationDTO response = service.save(reservation); // Llama al servicio para guardar la reserva
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Retorna la nueva reserva con estado CREATED
    }

    // Método para actualizar una reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        LOGGER.info("Updating a reservation with {}", id); // Registra la acción
        ReservationDTO response = service.update(id, reservation); // Llama al servicio para actualizar la reserva

        return new ResponseEntity<>(response, HttpStatus.OK); // Retorna la reserva actualizada con estado OK
    }

    // Método para eliminar una reserva por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("Deleting a reservation with {}", id); // Registra la acción
        service.delete(id); // Llama al servicio para eliminar la reserva
        return new ResponseEntity<>(HttpStatus.OK); // Retorna estado OK tras la eliminación
    }

}
