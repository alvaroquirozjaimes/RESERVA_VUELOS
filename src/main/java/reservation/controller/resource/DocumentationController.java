package reservation.controller.resource; // Paquete donde se encuentra el controlador

import io.swagger.v3.oas.annotations.Hidden; // Importa la anotación para ocultar la clase de la documentación de Swagger
import jakarta.servlet.http.HttpServletResponse; // Importa la clase para manejar la respuesta HTTP
import org.springframework.stereotype.Controller; // Importa la anotación para definir un controlador de Spring
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.RequestMapping; // Importa la anotación para mapear solicitudes
import org.springframework.web.bind.annotation.ResponseBody; // Importa la anotación para indicar que el método devuelve un cuerpo de respuesta
import java.io.IOException; // Importa la clase para manejar excepciones de entrada/salida

@Hidden // Indica que esta clase no debe aparecer en la documentación de Swagger
@Controller // Define la clase como un controlador de Spring
@RequestMapping("documentation") // Mapea las solicitudes a la ruta "/documentation"
public class DocumentationController {

    @ResponseBody // Indica que el método devuelve un cuerpo de respuesta
    @GetMapping // Maneja las solicitudes GET a "/documentation"
    public void redirectToDocumentation(HttpServletResponse response) {
        try {
            // Redirige a la URL de la interfaz de Swagger UI
            response.sendRedirect("swagger-ui.html");
        } catch (IOException e) { // Maneja excepciones de entrada/salida
            StringBuilder sb = new StringBuilder("UNEXPECTED ERROR: "); // Construye un mensaje de error
            if (e.getMessage() != null) { // Si hay un mensaje de error, lo agrega al mensaje
                sb.append(e.getMessage());
            }
            // Aquí podrías agregar un manejo adicional para registrar el error o enviar una respuesta de error
        }
    }
}
