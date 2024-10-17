package reservation.model;

import jakarta.persistence.*;  // Importa las anotaciones de Jakarta Persistence
import jakarta.validation.constraints.NotBlank;  // Importa la anotación para validar que un campo no esté vacío
import jakarta.validation.constraints.Past;  // Importa la anotación para validar que una fecha sea pasada
import jakarta.validation.constraints.Size;  // Importa la anotación para validar el tamaño de un campo

import java.time.LocalDate;  // Importa la clase LocalDate para manejar fechas
import java.util.Objects;  // Importa la clase Objects para operaciones sobre objetos

@Entity  // Indica que esta clase es una entidad de JPA
public class Passenger extends Base {  // La clase Passenger hereda de Base

    @Size(min = 1, max = 30)  // Valida que el tamaño del nombre esté entre 1 y 30 caracteres
    @NotBlank(message = "firstName is mandatory")  // Valida que el campo no esté vacío
    @Column(name = "first_name", nullable = false, length = 30)  // Mapea la columna en la tabla
    private String firstName;  // Nombre del pasajero

    @Size(min = 1, max = 30)  // Valida que el tamaño del apellido esté entre 1 y 30 caracteres
    @NotBlank(message = "lastName is mandatory")  // Valida que el campo no esté vacío
    @Column(name = "last_name", nullable = false, length = 30)  // Mapea la columna en la tabla
    private String lastName;  // Apellido del pasajero

    @Column(name = "document_number", nullable = false)  // Mapea la columna en la tabla
    private String documentNumber;  // Número de documento del pasajero

    @Column(name = "document_type", nullable = false)  // Mapea la columna en la tabla
    private String documentType;  // Tipo de documento del pasajero

    @Past(message = "birthday need to be a date in the past")  // Valida que la fecha de cumpleaños sea en el pasado
    @Column(name = "birthday", nullable = false)  // Mapea la columna en la tabla
    private LocalDate birthday;  // Fecha de cumpleaños del pasajero

    // Método para obtener el nombre del pasajero
    public String getFirstName() {
        return firstName;
    }

    // Método para establecer el nombre del pasajero
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Método para obtener el apellido del pasajero
    public String getLastName() {
        return lastName;
    }

    // Método para establecer el apellido del pasajero
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Método para obtener el número de documento del pasajero
    public String getDocumentNumber() {
        return documentNumber;
    }

    // Método para establecer el número de documento del pasajero
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    // Método para obtener el tipo de documento del pasajero
    public String getDocumentType() {
        return documentType;
    }

    // Método para establecer el tipo de documento del pasajero
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    // Método para obtener la fecha de cumpleaños del pasajero
    public LocalDate getBirthday() {
        return birthday;
    }

    // Método para establecer la fecha de cumpleaños del pasajero
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    // Método equals para comparar dos objetos de la clase Passenger
    @Override
    public boolean equals(Object o) {
        if (this == o)  // Si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass())  // Si el objeto es nulo o de otra clase
            return false;
        Passenger passenger = (Passenger) o;  // Convierte el objeto a tipo Passenger
        // Compara los campos id, firstName, lastName, documentNumber, documentType y birthday
        return Objects.equals(getId(), passenger.getId()) &&
                Objects.equals(firstName, passenger.firstName) &&
                Objects.equals(lastName, passenger.lastName) &&
                Objects.equals(documentNumber, passenger.documentNumber) &&
                Objects.equals(documentType, passenger.documentType) &&
                Objects.equals(birthday, passenger.birthday);
    }

    // Método hashCode para generar un código hash basado en id, firstName, lastName, documentNumber, documentType y birthday
    @Override
    public int hashCode() {
        return Objects.hash(getId(), firstName, lastName, documentNumber, documentType, birthday);
    }

    // Método toString para representar la clase como una cadena
    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", documentType='" + documentType + '\'' +
                ", birthday=" + birthday +
                '}';  // Representación en cadena del pasajero
    }
}
