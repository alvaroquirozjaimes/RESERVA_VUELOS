package org.Reservations.integration.resource; // Paquete donde se encuentra la interfaz ReservationResource

import io.swagger.v3.oas.annotations.Operation; // Importa la anotación para definir operaciones en la API
import io.swagger.v3.oas.annotations.Parameter; // Importa la anotación para definir parámetros en las operaciones
import io.swagger.v3.oas.annotations.enums.ParameterIn; // Importa enumeraciones para definir la ubicación de los parámetros
import io.swagger.v3.oas.annotations.media.Content; // Importa la anotación para definir el contenido de las respuestas
import io.swagger.v3.oas.annotations.media.ExampleObject; // Importa la anotación para definir ejemplos de contenido
import io.swagger.v3.oas.annotations.media.Schema; // Importa la anotación para definir esquemas de contenido
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Importa la anotación para definir respuestas de la API
import io.swagger.v3.oas.annotations.tags.Tag; // Importa la anotación para definir etiquetas de la API
import jakarta.validation.Valid; // Importa la anotación para validar objetos
import jakarta.validation.constraints.Min; // Importa la anotación para validar valores mínimos
 // Importa la clase DTO para manejar errores
 // Importa la clase DTO para manejar reservas
import org.springframework.http.MediaType; // Importa tipos de medios de respuesta
import org.springframework.http.ResponseEntity; // Importa ResponseEntity para manejar respuestas HTTP
import org.springframework.web.bind.annotation.*; // Importa anotaciones para manejar solicitudes web
import reservation.dto.ErrorDTO;
import reservation.dto.ReservationDTO;

import java.util.List; // Importa la clase List para manejar colecciones

// Define la interfaz ReservationResource para operaciones sobre la entidad de reservas
@Tag(name = "Reservation", description = "Operations about the reservation entity")
public interface ReservationResource {

    // Método para obtener todas las reservas
    @Operation(description = "Get the information of all the reservations", responses = {
            @ApiResponse(responseCode = "200", description = "Return the information of all the reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain the reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))})
    public ResponseEntity<List<ReservationDTO>> getReservations();

    // Método para obtener una reserva por su ID
    @Operation(description = "Get the information about one reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Return the information of one reservation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain the reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to search", example = "1")})
    public ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable Long id);

    // Método para crear una nueva reserva
    @Operation(description = "Create one reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Return the created reservation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request of the information to persist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain the reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(name = "Reservation", summary = "Example reservation to create", value = "{\n"
                    + "    \"passengers\": [\n" + "        {\n" + "            \"firstName\": \"Andres\",\n"
                    + "            \"lastName\": \"Sacco\",\n" + "            \"documentNumber\": \"12345678\",\n"
                    + "            \"documentType\": \"DNI\",\n" + "            \"birthday\": \"1985-01-01\"\n"
                    + "        }\n" + "    ],\n" + "    \"itinerary\": {\n" + "        \"segment\": [\n"
                    + "            {\n" + "                \"origin\": \"BUE\",\n"
                    + "                \"destination\": \"MIA\",\n" + "                \"departure\": \"2024-12-31\",\n"
                    + "                \"arrival\": \"2025-01-01\",\n" + "                \"carrier\": \"AA\"\n"
                    + "            }\n" + "        ],\n" + "        \"price\": {\n" + "            \"totalPrice\": 1,\n"
                    + "            \"totalTax\": 0,\n" + "            \"basePrice\": 1\n" + "        }\n" + "    }\n"
                    + "}"))))
    public ResponseEntity<ReservationDTO> save(@RequestBody @Valid ReservationDTO reservation);

    // Método para actualizar una reserva existente
    @Operation(description = "Update one reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Return the updated reservation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request of the information to persist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain the reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to update", example = "1")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(name = "Reservation", summary = "Example reservation to create", value = "{\n"
                    + "  \"id\": 1,\n" + "  \"passengers\": [\n" + "    {\n"
                    + "      \"firstName\": \"Andres\",\n" + "      \"lastName\": \"Sacco\",\n"
                    + "      \"documentNumber\": \"12345678\",\n" + "      \"documentType\": \"DNI\",\n"
                    + "      \"birthday\": \"1985-01-01\"\n" + "    }\n" + "  ],\n" + "  \"itinerary\": {\n"
                    + "    \"segment\": [\n" + "      {\n" + "        \"origin\": \"EZE\",\n"
                    + "        \"destination\": \"MIA\",\n" + "        \"departure\": \"2024-12-31\",\n"
                    + "        \"arrival\": \"2025-01-01\",\n" + "        \"carrier\": \"AA\"\n" + "      }\n"
                    + "    ],\n" + "    \"price\": {\n" + "      \"totalPrice\": 1,\n"
                    + "      \"totalTax\": 0,\n" + "      \"basePrice\": 1\n" + "    }\n" + "  }\n" + "}"))))
    public ResponseEntity<ReservationDTO> update(@Min(1) @PathVariable Long id,
                                                 @RequestBody @Valid ReservationDTO reservation);

    // Método para eliminar una reserva
    @Operation(description = "Delete one reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Return nothing", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Something bad happens to obtain the reservations", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to delete", example = "1")})
    public ResponseEntity<Void> delete(@Min(1) @PathVariable Long id);
}
