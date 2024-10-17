package reservation.dto;

import java.math.BigDecimal; // Importa la clase BigDecimal para manejar precios de forma precisa

// Clase que representa el precio en el sistema de reservas
public class PriceDTO {

    private BigDecimal totalPrice; // Precio total (incluye impuestos)
    private BigDecimal totalTax;    // Total de impuestos aplicados
    private BigDecimal basePrice;    // Precio base (sin impuestos)

    // Método que devuelve el precio total
    public BigDecimal getTotalPrice() {
        return totalPrice; // Retorna el precio total
    }

    // Método que establece el precio total
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice; // Asigna el precio total
    }

    // Método que devuelve el total de impuestos
    public BigDecimal getTotalTax() {
        return totalTax; // Retorna el total de impuestos
    }

    // Método que establece el total de impuestos
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax; // Asigna el total de impuestos
    }

    // Método que devuelve el precio base
    public BigDecimal getBasePrice() {
        return basePrice; // Retorna el precio base
    }

    // Método que establece el precio base
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice; // Asigna el precio base
    }
}
