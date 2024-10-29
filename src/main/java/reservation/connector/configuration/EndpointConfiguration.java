package reservation.connector.configuration;

// Esta clase se utiliza para configurar los parámetros de conexión de un endpoint.
public class EndpointConfiguration {

    // URL del endpoint al que se conectará.
    private String url;

    // Tiempo de espera para la lectura de datos desde el endpoint (en milisegundos).
    private int readTimeout;

    // Tiempo de espera para la escritura de datos en el endpoint (en milisegundos).
    private int writeTimeout;

    // Tiempo de espera para establecer una conexión con el endpoint (en milisegundos).
    private int connectionTimeout;

    // Método para obtener la URL del endpoint.
    public String getUrl() {
        return url;
    }

    // Método para establecer la URL del endpoint.
    public void setUrl(String url) {
        this.url = url;
    }

    // Método para obtener el tiempo de espera de lectura.
    public int getReadTimeout() {
        return readTimeout;
    }

    // Método para establecer el tiempo de espera de lectura.
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    // Método para obtener el tiempo de espera de conexión.
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    // Método para establecer el tiempo de espera de conexión.
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    // Método para obtener el tiempo de espera de escritura.
    public int getWriteTimeout() {
        return writeTimeout;
    }

    // Método para establecer el tiempo de espera de escritura.
    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }
}
