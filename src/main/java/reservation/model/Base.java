package reservation.model;

import jakarta.persistence.*;  // Importa las anotaciones de Jakarta Persistence
import java.util.Objects;  // Importa la clase Objects para operaciones sobre objetos

// Clase base para entidades que necesitan un identificador y control de versión
@MappedSuperclass  // Indica que esta clase es una superclase mapeada
public class Base {

    @Id  // Marca el campo como el identificador de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica que el ID se generará automáticamente
    private Long id;  // ID de la entidad

    @Version  // Marca el campo como un campo de versión para control de concurrencia
    Long version;  // Versión de la entidad

    // Método para obtener el ID
    public Long getId() {
        return id;
    }

    // Método para establecer el ID
    public void setId(Long id) {
        this.id = id;
    }

    // Método para obtener la versión
    public Long getVersion() {
        return version;
    }

    // Método para establecer la versión
    public void setVersion(Long version) {
        this.version = version;
    }

    // Método equals para comparar dos objetos de la clase Base
    @Override
    public boolean equals(Object o) {
        if (this == o)  // Si son la misma instancia
            return true;
        if (o == null || getClass() != o.getClass())  // Si el objeto es nulo o de otra clase
            return false;
        Base base = (Base) o;  // Convierte el objeto a tipo Base
        // Compara los campos id y version
        return Objects.equals(id, base.id) && Objects.equals(version, base.version);
    }

    // Método hashCode para generar un código hash basado en id y version
    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    // Método toString para representar la clase como una cadena
    @Override
    public String toString() {
        return "Base{" + "id=" + id + ", version=" + version + '}';  // Representación en cadena
    }
}

