package reservation.model;

import jakarta.persistence.*;  // Importa las anotaciones de Jakarta Persistence
import java.math.BigDecimal;  // Importa la clase BigDecimal para manejar precios con alta precisión
import java.util.Objects;  // Importa la clase Objects para operaciones sobre objetos

@Entity  // Indica que esta clase es una entidad de JPA
public class Price extends Base {  // La clase Price hereda de Base

    @Column(name = "total_price", nullable = false)  // Mapea la columna total_price en la tabla, no puede ser nula
    private BigDecimal totalPrice;  // Precio total de la reserva

    @Column(name = "total_tax", nullable = false)  // Mapea la columna total_tax en la tabla, no puede ser nula
    private BigDecimal totalTax;  // Impuesto total aplicado a la reserva

    @Column(name = "base_price", nullable = false)  // Mapea la columna base_price en la tabla, no puede ser nula
    private BigDecimal basePrice;  // Precio base antes de impuestos

    // Método para obtener el precio total
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    // Método para establecer el precio total
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Método para obtener el impuesto total
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    // Método para establecer el impuesto total
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    // Método para obtener el precio base
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    // Método para establecer el precio base
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    // Método equals para comparar dos objetos de la clase Price
    @Override
    public boolean equals(Object o) {
        if (this == o)  // Si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass())  // Si el objeto es nulo o de otra clase
            return false;
        Price price = (Price) o;  // Convierte el objeto a tipo Price
        // Compara los campos id, totalPrice, totalTax y basePrice
        return Objects.equals(getId(), price.getId()) &&
                Objects.equals(totalPrice, price.totalPrice) &&
                Objects.equals(totalTax, price.totalTax) &&
                Objects.equals(basePrice, price.basePrice);
    }

    // Método hashCode para generar un código hash basado en id, totalPrice, totalTax y basePrice
    @Override
    public int hashCode() {
        return Objects.hash(getId(), totalPrice, totalTax, basePrice);
    }

    // Método toString para representar la clase como una cadena
    @Override
    public String toString() {
        return "Price{" +
                "id=" + getId() +
                ", totalPrice=" + totalPrice +
                ", totalTax=" + totalTax +
                ", basePrice=" + basePrice +
                '}';  // Representación en cadena del precio
    }
}
