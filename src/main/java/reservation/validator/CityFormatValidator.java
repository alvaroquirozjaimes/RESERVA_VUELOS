package reservation.validator;// Especifica el paquete al que pertenece la clase.

import jakarta.validation.ConstraintValidator; // Importa la interfaz ConstraintValidator para implementar la lógica de validación.
import jakarta.validation.ConstraintValidatorContext; // Importa el contexto para la validación.

public class CityFormatValidator implements ConstraintValidator<CityFormatConstraint, String> {

    // Este método se llama al inicializar el validador.
    @Override
    public void initialize(CityFormatConstraint contactNumber) {
        // No se necesita lógica de inicialización en este caso.
    }

    // Este método se encarga de validar el formato del campo.
    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        // Comprueba si el campo no es nulo, contiene solo letras mayúsculas y tiene una longitud de 3 caracteres.
        return field != null && field.matches("[A-Z]+") && field.length() == 3;
    }
}
