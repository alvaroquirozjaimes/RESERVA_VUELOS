package reservation.dto;

import jakarta.validation.constraints.NotBlank; // Importa la anotación para validar que un campo no esté vacío
import jakarta.validation.constraints.Past; // Importa la anotación para validar que una fecha esté en el pasado

import java.time.LocalDate; // Importa la clase LocalDate para manejar fechas
import java.util.Objects; // Importa la clase Objects para métodos de comparación y hash

// Clase Data Transfer Object (DTO) que representa un pasajero
public class PassengerDTO {

    // Anotación que valida que el campo firstName no esté vacío
    @NotBlank(message = "firstName is mandatory")
    private String firstName; // Nombre del pasajero

    // Anotación que valida que el campo lastName no esté vacío
    @NotBlank(message = "lastName is mandatory")
    private String lastName; // Apellido del pasajero

    private String documentNumber; // Número de documento del pasajero
    private String documentType; // Tipo de documento del pasajero

    // Anotación que valida que la fecha de cumpleaños esté en el pasado
    @Past(message = "birthday need to be a date in the past")
    private LocalDate birthday; // Fecha de cumpleaños del pasajero

    // Método getter para obtener el nombre del pasajero
    public String getFirstName() {
        return firstName;
    }

    // Método setter para establecer el nombre del pasajero
    public void setFirstName(String firstName) {
        this.firstName = firstName; // Asigna el nombre
    }

    // Método getter para obtener el apellido del pasajero
    public String getLastName() {
        return lastName;
    }

    // Método setter para establecer el apellido del pasajero
    public void setLastName(String lastName) {
        this.lastName = lastName; // Asigna el apellido
    }

    // Método getter para obtener el número de documento del pasajero
    public String getDocumentNumber() {
        return documentNumber;
    }

    // Método setter para establecer el número de documento del pasajero
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber; // Asigna el número de documento
    }

    // Método getter para obtener el tipo de documento del pasajero
    public String getDocumentType() {
        return documentType;
    }

    // Método setter para establecer el tipo de documento del pasajero
    public void setDocumentType(String documentType) {
        this.documentType = documentType; // Asigna el tipo de documento
    }

    // Método getter para obtener la fecha de cumpleaños del pasajero
    public LocalDate getBirthday() {
        return birthday;
    }

    // Método setter para establecer la fecha de cumpleaños del pasajero
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday; // Asigna la fecha de cumpleaños
    }

    // Método que compara si dos objetos PassengerDTO son iguales
    @Override
    public boolean equals(Object o) {
        if (this == o) // Compara si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass()) // Verifica si el objeto es nulo o de una clase diferente
            return false;
        PassengerDTO that = (PassengerDTO) o; // Convierte el objeto a PassengerDTO
        // Compara los atributos para determinar igualdad
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
                && Objects.equals(documentNumber, that.documentNumber)
                && Objects.equals(documentType, that.documentType) && Objects.equals(birthday, that.birthday);
    }

    // Método que genera un código hash para el objeto PassengerDTO
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, documentNumber, documentType, birthday); // Utiliza el método hash para los atributos relevantes
    }

    // Método que devuelve una representación en cadena del objeto PassengerDTO
    @Override
    public String toString() {
        return "PassengerDTO{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
                + ", documentNumber='" + documentNumber + '\'' + ", documentType='" + documentType + '\''
                + ", birthday=" + birthday + '}'; // Representa los atributos en formato de cadena
    }
}
