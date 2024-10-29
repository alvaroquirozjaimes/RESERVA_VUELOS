package reservation.connector.response; // Indica el paquete al que pertenece esta clase.

public class CityDTO { // Declaración de la clase pública CityDTO.

    private String name; // Atributo para almacenar el nombre de la ciudad.
    private String code; // Atributo para almacenar el código de la ciudad.
    private String timeZone; // Atributo para almacenar la zona horaria de la ciudad.

    // Método getter para obtener el nombre de la ciudad.
    public String getName() {
        return name; // Devuelve el nombre de la ciudad.
    }

    // Método setter para establecer el nombre de la ciudad.
    public void setName(String name) {
        this.name = name; // Asigna el nombre proporcionado al atributo name.
    }

    // Método getter para obtener el código de la ciudad.
    public String getCode() {
        return code; // Devuelve el código de la ciudad.
    }

    // Método setter para establecer el código de la ciudad.
    public void setCode(String code) {
        this.code = code; // Asigna el código proporcionado al atributo code.
    }

    // Método getter para obtener la zona horaria de la ciudad.
    public String getTimeZone() {
        return timeZone; // Devuelve la zona horaria de la ciudad.
    }

    // Método setter para establecer la zona horaria de la ciudad.
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone; // Asigna la zona horaria proporcionada al atributo timeZone.
    }
}
