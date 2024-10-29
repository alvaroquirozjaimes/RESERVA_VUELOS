package reservation.connector.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

// Anotación que indica que esta clase es una clase de configuración en Spring.
// Los valores se cargarán desde las propiedades de la aplicación (por ejemplo, application.yml o application.properties).
@Configuration
@ConfigurationProperties(prefix = "http-connector")
public class HttpConnectorConfiguration {

    // Mapa que almacena configuraciones de host, donde la clave es el nombre del host
    // y el valor es un objeto HostConfiguration que contiene la configuración del host.
    private HashMap<String, HostConfiguration> hosts;

    // Método para obtener el mapa de hosts configurados.
    public HashMap<String, HostConfiguration> getHosts() {
        return hosts;
    }

    // Método para establecer el mapa de hosts configurados.
    public void setHosts(HashMap<String, HostConfiguration> hosts) {
        this.hosts = hosts;
    }
}
