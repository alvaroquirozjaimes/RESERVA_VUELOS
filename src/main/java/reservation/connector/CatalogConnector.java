package reservation.connector;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reservation.connector.configuration.EndpointConfiguration;
import reservation.connector.configuration.HostConfiguration;
import reservation.connector.configuration.HttpConnectorConfiguration;
import reservation.connector.response.CityDTO;

import java.util.concurrent.TimeUnit;

@Component
public class CatalogConnector {

    // Logger para registrar información, advertencias y errores
    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogConnector.class);

    // Nombre del host que se utilizará para realizar la llamada a la API
    private final String HOST = "api-catalog";

    // Nombre del endpoint específico que se utilizará para obtener la ciudad
    private final String ENDPOINT = "get-city";

    // Configuración HTTP inyectada a través del constructor
    private HttpConnectorConfiguration configuration;

    // Constructor que inyecta la configuración HTTP al conector
    @Autowired
    public CatalogConnector(HttpConnectorConfiguration configuration) {
        this.configuration = configuration;
    }

    // Método que obtiene la información de la ciudad utilizando su código
    public CityDTO getCity(String code) {
        LOGGER.info("calling to api-catalog"); // Registro de la llamada a la API

        // Obtener la configuración del host desde la configuración inyectada
        HostConfiguration hostConfiguration = configuration.getHosts().get(HOST);
        // Obtener la configuración del endpoint específico desde el host
        EndpointConfiguration endpointConfiguration = hostConfiguration.getEndpoints().get(ENDPOINT);

        // Crear un cliente HTTP utilizando Netty con configuraciones específicas
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
                        Math.toIntExact(endpointConfiguration.getConnectionTimeout())) // Establecer el tiempo de conexión
                .doOnConnected(conn -> conn
                        .addHandler(new ReadTimeoutHandler(endpointConfiguration.getReadTimeout(), TimeUnit.MILLISECONDS)) // Manejador de tiempo de espera de lectura
                        .addHandler(new WriteTimeoutHandler(endpointConfiguration.getWriteTimeout(), TimeUnit.MILLISECONDS))); // Manejador de tiempo de espera de escritura

        // Construir un WebClient que se utilizará para realizar las solicitudes HTTP
        WebClient client = WebClient.builder()
                .baseUrl("http://" + hostConfiguration.getHost() + ":" + hostConfiguration.getPort() + endpointConfiguration.getUrl()) // Construir la URL base
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // Establecer el tipo de contenido
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) // Establecer el encabezado de aceptación
                .clientConnector(new ReactorClientHttpConnector(httpClient)) // Conectar el cliente HTTP
                .build(); // Construir el WebClient

        // Realizar una llamada GET al endpoint de la API, recuperar el cuerpo de la respuesta como un objeto CityDTO
        return client.get()
                .uri(urlEncoder -> urlEncoder.build(code)) // Construir la URI con el código de la ciudad
                .retrieve() // Recuperar la respuesta
                .bodyToMono(CityDTO.class) // Convertir el cuerpo a un objeto CityDTO
                .share() // Compartir la secuencia reactiva
                .block(); // Bloquear hasta que se reciba la respuesta
    }
}
