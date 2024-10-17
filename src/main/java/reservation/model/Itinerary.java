package reservation.model;

import jakarta.persistence.*;  // Importa las anotaciones de Jakarta Persistence
import jakarta.validation.Valid;  // Importa la anotación para validar objetos
import jakarta.validation.constraints.NotEmpty;  // Importa la anotación para validar que un campo no esté vacío
import org.hibernate.annotations.Cascade;  // Importa las anotaciones de cascada de Hibernate
import org.hibernate.annotations.CascadeType;  // Importa tipos de cascada

import java.util.List;  // Importa la clase List para trabajar con listas
import java.util.Objects;  // Importa la clase Objects para operaciones sobre objetos

@Entity  // Indica que esta clase es una entidad de JPA
public class Itinerary extends Base {  // La clase Itinerary hereda de Base

    @Valid  // Indica que el objeto debe ser validado
    @NotEmpty(message = "You need at least one segment")  // Valida que la lista no esté vacía
    @OneToMany(fetch = FetchType.LAZY)  // Define una relación uno a muchos con Segment
    @Cascade(CascadeType.ALL)  // Indica que las operaciones en Itinerary se aplicarán a Segment
    @JoinColumn(name = "itinerary_id")  // Especifica la columna de unión en la tabla Segment
    private List<Segment> segment;  // Lista de segmentos del itinerario

    @OneToOne(fetch = FetchType.LAZY)  // Define una relación uno a uno con Price
    @Cascade(CascadeType.ALL)  // Indica que las operaciones en Itinerary se aplicarán a Price
    @JoinColumn(name = "itinerary_id")  // Especifica la columna de unión en la tabla Price
    private Price price;  // Precio asociado al itinerario

    // Método para obtener la lista de segmentos
    public List<Segment> getSegment() {
        return segment;
    }

    // Método para establecer la lista de segmentos
    public void setSegment(List<Segment> segment) {
        this.segment = segment;
    }

    // Método para obtener el precio del itinerario
    public Price getPrice() {
        return price;
    }

    // Método para establecer el precio del itinerario
    public void setPrice(Price price) {
        this.price = price;
    }

    // Método equals para comparar dos objetos de la clase Itinerary
    @Override
    public boolean equals(Object o) {
        if (this == o)  // Si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass())  // Si el objeto es nulo o de otra clase
            return false;
        Itinerary itinerary = (Itinerary) o;  // Convierte el objeto a tipo Itinerary
        // Compara los campos id, segment y price
        return Objects.equals(getId(), itinerary.getId()) &&
                Objects.equals(segment, itinerary.segment) &&
                Objects.equals(price, itinerary.price);
    }

    // Método hashCode para generar un código hash basado en id, segment y price
    @Override
    public int hashCode() {
        return Objects.hash(getId(), segment, price);
    }

    // Método toString para representar la clase como una cadena
    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + getId() +
                ", segment=" + segment +
                ", price=" + price +
                '}';  // Representación en cadena del itinerario
    }
}
