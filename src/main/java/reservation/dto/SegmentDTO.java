package reservation.dto;

// Clase que representa un segmento de viaje
public class SegmentDTO {

    // Atributo que almacena el origen del segmento
    private String origin;

    // Atributo que almacena el destino del segmento
    private String destination;

    // Atributo que almacena la fecha y hora de salida
    private String departure;

    // Atributo que almacena la fecha y hora de llegada
    private String arrival;

    // Atributo que almacena la compañía aérea o transportista
    private String carrier;

    // Método para obtener el origen del segmento
    public String getOrigin() {
        return origin; // Retorna el origen del segmento
    }

    // Método para establecer el origen del segmento
    public void setOrigin(String origin) {
        this.origin = origin; // Asigna el valor al atributo origin
    }

    // Método para obtener el destino del segmento
    public String getDestination() {
        return destination; // Retorna el destino del segmento
    }

    // Método para establecer el destino del segmento
    public void setDestination(String destination) {
        this.destination = destination; // Asigna el valor al atributo destination
    }

    // Método para obtener la fecha y hora de salida
    public String getDeparture() {
        return departure; // Retorna la fecha y hora de salida
    }

    // Método para establecer la fecha y hora de salida
    public void setDeparture(String departure) {
        this.departure = departure; // Asigna el valor al atributo departure
    }

    // Método para obtener la fecha y hora de llegada
    public String getArrival() {
        return arrival; // Retorna la fecha y hora de llegada
    }

    // Método para establecer la fecha y hora de llegada
    public void setArrival(String arrival) {
        this.arrival = arrival; // Asigna el valor al atributo arrival
    }

    // Método para obtener el transportista o compañía aérea
    public String getCarrier() {
        return carrier; // Retorna la compañía aérea o transportista
    }

    // Método para establecer el transportista o compañía aérea
    public void setCarrier(String carrier) {
        this.carrier = carrier; // Asigna el valor al atributo carrier
    }
}
