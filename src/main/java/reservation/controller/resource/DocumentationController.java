package reservation.controller.resource;

import io.swagger.v3.oas.annotations.Hidden; // Importa la anotación para ocultar el controlador en la documentación de Swagger
import jakarta.servlet.http.HttpServletResponse; // Importa la clase para gestionar la respuesta HTTP
import org.springframework.stereotype.Controller; // Importa la anotación para definir un controlador
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.RequestMapping; // Importa la anotación para especificar la ruta del controlador
import org.springframework.web.bind.annotation.ResponseBody; // Importa la anotación para indicar que el cuerpo de la respuesta será manejado directamente
import java.io.IOException; // Importa la clase IOException para manejar excepciones de entrada/salida

@Hidden // Indica que este controlador debe ser ocultado en la documentación generada por Swagger
@Controller // Indica que esta clase es un controlador de Spring MVC
@RequestMapping("documentation") // Especifica que todas las rutas en este controlador comienzan con "documentation"
public class DocumentationController {

    @ResponseBody // Indica que el retorno del método será el cuerpo de la respuesta
    @GetMapping // Mapea las solicitudes GET a este método
    public void redirectToDocumentation(HttpServletResponse response) {
        try {
            // Redirige al usuario a la URL "swagger-ui.html"
            response.sendRedirect("swagger-ui.html");
        } catch (IOException e) {
            // Manejo de excepciones en caso de error durante la redirección
            StringBuilder sb = new StringBuilder("UNEXPECTED ERROR: ");
            if (e.getMessage() != null) {
                sb.append(e.getMessage()); // Agrega el mensaje de la excepción al StringBuilder
            }
            // Aquí podrías registrar el error o lanzar una excepción personalizada si lo deseas
        }
    }
}
