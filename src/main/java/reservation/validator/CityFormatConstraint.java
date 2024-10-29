package reservation.validator;// Especifica el paquete al que pertenece la clase.

import jakarta.validation.Constraint; // Importa la anotación Constraint para definir restricciones de validación.
import jakarta.validation.Payload; // Importa la clase Payload para agregar información adicional a la validación.
import reservation.validator.CityFormatValidator;

import java.lang.annotation.*; // Importa las anotaciones de Java.

@Documented // Indica que esta anotación debe ser documentada por herramientas de documentación (como Javadoc).
@Constraint(validatedBy = CityFormatValidator.class) // Define que esta anotación es una restricción de validación y especifica la clase que implementa la lógica de validación.
@Target({ ElementType.METHOD, ElementType.FIELD }) // Indica que esta anotación puede aplicarse a métodos y campos.
@Retention(RetentionPolicy.RUNTIME) // Indica que la anotación estará disponible en tiempo de ejecución.
public @interface CityFormatConstraint {

    // Mensaje de error que se mostrará si la validación falla.
    String message() default "Invalid format of the city";

    // Grupos a los que esta restricción pertenece, útil para agrupar validaciones.
    Class<?>[] groups() default {};

    // Carga útil adicional para la restricción, puede ser utilizada por los validadores.
    Class<? extends Payload>[] payload() default {};
}
