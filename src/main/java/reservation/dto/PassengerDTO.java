package reservation.dto;

import java.time.LocalDate; // Importa la clase LocalDate para manejar fechas

// Clase que representa un pasajero en el sistema de reservas
public class PassengerDTO {

    private String firstName; // Nombre del pasajero
    private String lastName; // Apellido del pasajero
    private String documentNumber; // Número de documento del pasajero
    private String documentType; // Tipo de documento del pasajero (ej. DNI, Pasaporte)
    private LocalDate birthday; // Fecha de nacimiento del pasajero

    // Método que devuelve el nombre del pasajero
    public String getFirstName() {
        return firstName; // Retorna el nombre
    }

    // Método que establece el nombre del pasajero
    public void setFirstName(String firstName) {
        this.firstName = firstName; // Asigna el nombre
    }

    // Método que devuelve el apellido del pasajero
    public String getLastName() {
        return lastName; // Retorna el apellido
    }

    // Método que establece el apellido del pasajero
    public void setLastName(String lastName) {
        this.lastName = lastName; // Asigna el apellido
    }

    // Método que devuelve el número de documento del pasajero
    public String getDocumentNumber() {
        return documentNumber; // Retorna el número de documento
    }

    // Método que establece el número de documento del pasajero
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber; // Asigna el número de documento
    }

    // Método que devuelve el tipo de documento del pasajero
    public String getDocumentType() {
        return documentType; // Retorna el tipo de documento
    }

    // Método que establece el tipo de documento del pasajero
    public void setDocumentType(String documentType) {
        this.documentType = documentType; // Asigna el tipo de documento
    }

    // Método que devuelve la fecha de nacimiento del pasajero
    public LocalDate getBirthday() {
        return birthday; // Retorna la fecha de nacimiento
    }

    // Método que establece la fecha de nacimiento del pasajero
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday; // Asigna la fecha de nacimiento
    }
}
