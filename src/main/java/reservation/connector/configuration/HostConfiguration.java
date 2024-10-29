package reservation.connector.configuration;

import java.util.HashMap;

// Esta clase se utiliza para configurar la conexión a un host específico,
// que puede tener múltiples endpoints asociados.
public class HostConfiguration {

    // Dirección del host al que se conectará (por ejemplo, "localhost" o "api.example.com").
    private String host;

    // Número de puerto en el que el host está escuchando (por ejemplo, 80 para HTTP o 443 para HTTPS).
    private int port;

    // Mapa que almacena configuraciones de endpoint, donde la clave es el nombre del endpoint
    // y el valor es un objeto EndpointConfiguration que contiene la configuración de ese endpoint.
    private HashMap<String, EndpointConfiguration> endpoints;

    // Método para obtener la dirección del host.
    public String getHost() {
        return host;
    }

    // Método para establecer la dirección del host.
    public void setHost(String host) {
        this.host = host;
    }

    // Método para obtener el número de puerto.
    public int getPort() {
        return port;
    }

    // Método para establecer el número de puerto.
    public void setPort(int port) {
        this.port = port;
    }

    // Método para obtener el mapa de endpoints.
    public HashMap<String, EndpointConfiguration> getEndpoints() {
        return endpoints;
    }

    // Método para establecer el mapa de endpoints.
    public void setEndpoints(HashMap<String, EndpointConfiguration> endpoints) {
        this.endpoints = endpoints;
    }
}
