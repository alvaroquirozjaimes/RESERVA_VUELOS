package reservation.dto;

import java.math.BigDecimal; // Importa la clase BigDecimal para manejar valores decimales con precisión
import java.util.Objects; // Importa la clase Objects para métodos de comparación y hash

// Clase Data Transfer Object (DTO) que representa los precios relacionados con una reserva
public class PriceDTO {

    private BigDecimal totalPrice; // Precio total de la reserva
    private BigDecimal totalTax; // Total de impuestos aplicables a la reserva
    private BigDecimal basePrice; // Precio base antes de impuestos

    // Método getter para obtener el precio total
    public BigDecimal getTotalPrice() {
        return totalPrice; // Devuelve el precio total
    }

    // Método setter para establecer el precio total
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice; // Asigna el precio total
    }

    // Método getter para obtener el total de impuestos
    public BigDecimal getTotalTax() {
        return totalTax; // Devuelve el total de impuestos
    }

    // Método setter para establecer el total de impuestos
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax; // Asigna el total de impuestos
    }

    // Método getter para obtener el precio base
    public BigDecimal getBasePrice() {
        return basePrice; // Devuelve el precio base
    }

    // Método setter para establecer el precio base
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice; // Asigna el precio base
    }

    // Método que compara si dos objetos PriceDTO son iguales
    @Override
    public boolean equals(Object o) {
        if (this == o) // Compara si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass()) // Verifica si el objeto es nulo o de una clase diferente
            return false;
        PriceDTO priceDTO = (PriceDTO) o; // Convierte el objeto a PriceDTO
        // Compara los atributos para determinar igualdad
        return Objects.equals(totalPrice, priceDTO.totalPrice) && Objects.equals(totalTax, priceDTO.totalTax)
                && Objects.equals(basePrice, priceDTO.basePrice);
    }

    // Método que genera un código hash para el objeto PriceDTO
    @Override
    public int hashCode() {
        return Objects.hash(totalPrice, totalTax, basePrice); // Utiliza el método hash para los atributos relevantes
    }

    // Método que devuelve una representación en cadena del objeto PriceDTO
    @Override
    public String toString() {
        return "PriceDTO{" + "totalPrice=" + totalPrice + ", totalTax=" + totalTax + ", basePrice=" + basePrice + '}';
        // Representa los atributos en formato de cadena
    }
}
