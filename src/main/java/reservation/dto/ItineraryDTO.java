package reservation.dto;

import jakarta.validation.Valid; // Importa la anotación para validar objetos anidados
import java.util.List; // Importa la clase List para manejar listas de elementos
import java.util.Objects; // Importa la clase Objects para métodos de comparación y hash

// Clase Data Transfer Object (DTO) que representa un itinerario
public class ItineraryDTO {

    @Valid // Indica que los elementos de la lista segment deben ser validados
    private List<SegmentDTO> segment; // Lista de segmentos que conforman el itinerario

    private PriceDTO price; // Precio asociado al itinerario

    // Método getter para obtener la lista de segmentos
    public List<SegmentDTO> getSegment() {
        return segment;
    }

    // Método setter para establecer la lista de segmentos
    public void setSegment(List<SegmentDTO> segment) {
        this.segment = segment; // Asigna la lista de segmentos
    }

    // Método getter para obtener el precio del itinerario
    public PriceDTO getPrice() {
        return price;
    }

    // Método setter para establecer el precio del itinerario
    public void setPrice(PriceDTO price) {
        this.price = price; // Asigna el precio al itinerario
    }

    // Método que compara si dos objetos ItineraryDTO son iguales
    @Override
    public boolean equals(Object o) {
        if (this == o) // Compara si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass()) // Verifica si el objeto es nulo o de una clase diferente
            return false;
        ItineraryDTO that = (ItineraryDTO) o; // Convierte el objeto a ItineraryDTO
        // Compara los segmentos y el precio para determinar igualdad
        return Objects.equals(segment, that.segment) && Objects.equals(price, that.price);
    }

    // Método que genera un código hash para el objeto ItineraryDTO
    @Override
    public int hashCode() {
        return Objects.hash(segment, price); // Utiliza el método hash para los atributos relevantes
    }

    // Método que devuelve una representación en cadena del objeto ItineraryDTO
    @Override
    public String toString() {
        return "ItineraryDTO{" + "segment=" + segment + ", price=" + price + '}'; // Representa los atributos en formato de cadena
    }
}
