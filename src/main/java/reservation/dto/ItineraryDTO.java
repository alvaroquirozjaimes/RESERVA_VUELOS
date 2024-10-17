package reservation.dto;

import java.util.List; // Importa la clase List para manejar colecciones de segmentos

// Clase que representa un itinerario en el sistema de reservas
public class ItineraryDTO {

    private Long id; // Identificador único del itinerario
    private Long version; // Versión del itinerario para control de concurrencia
    private List<SegmentDTO> segment; // Lista de segmentos que componen el itinerario
    private PriceDTO price; // Objeto que contiene información sobre el precio del itinerario

    // Método que devuelve la lista de segmentos del itinerario
    public List<SegmentDTO> getSegment() {
        return segment; // Retorna la lista de segmentos
    }

    // Método que establece la lista de segmentos del itinerario
    public void setSegment(List<SegmentDTO> segment) {
        this.segment = segment; // Asigna la lista de segmentos
    }

    // Método que devuelve el objeto PriceDTO asociado al itinerario
    public PriceDTO getPrice() {
        return price; // Retorna el objeto PriceDTO
    }

    // Método que establece el objeto PriceDTO del itinerario
    public void setPrice(PriceDTO price) {
        this.price = price; // Asigna el objeto PriceDTO
    }

    // Método que devuelve el identificador del itinerario
    public Long getId() {
        return id; // Retorna el identificador
    }

    // Método que establece el identificador del itinerario
    public void setId(Long id) {
        this.id = id; // Asigna el identificador
    }

    // Método que devuelve la versión del itinerario
    public Long getVersion() {
        return version; // Retorna la versión
    }

    // Método que establece la versión del itinerario
    public void setVersion(Long version) {
        this.version = version; // Asigna la versión
    }
}
