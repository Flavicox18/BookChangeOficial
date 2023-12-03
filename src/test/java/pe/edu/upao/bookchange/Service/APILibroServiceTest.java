package pe.edu.upao.bookchange.Service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import pe.edu.upao.bookchange.entity.Libro;
import pe.edu.upao.bookchange.exception.PersonalizadoException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
public class APILibroServiceTest {

    private static final String API_KEY = "AIzaSyBPK8cRF63mvv5VneDOMLh-x5OJa_ASrgI";

    @Test
    public void testGoogleAPI() {
        // Crear un objeto Libro con datos de prueba
        Libro libro = new Libro();
        libro.setNombre("Nombre del Libro");
        libro.setAutor("Autor del Libro");
        libro.setEditorial("Editorial del Libro");
        libro.setIsbn(1234567890L);
        libro.setEstado("disponible");
        libro.setFechaLanzamiento(new Date());
        libro.setSinopsis("Sinopsis del Libro");
        libro.setFoto("URL de la foto del Libro");
        libro.setGenero("Acción");

        // Obtener la dirección a partir de los atributos del Libro
        String direccion = String.format(
                "%s, %s, %s, %s",
                libro.getNombre(),
                libro.getAutor(),
                libro.getEditorial(),
                libro.getSinopsis()
        );

        // Construir la URL de la API de Google Maps con la dirección y la clave API
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + direccion + "&key=" + API_KEY;

        // Realizar la solicitud a la API
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(apiUrl, String.class);

        // Verificar que la respuesta sea exitosa (código de estado 200)
        assertEquals(200, responseEntity.getStatusCodeValue());

        // Verificar que la respuesta no esté vacía
        assertNotNull(responseEntity.getBody());

        System.out.println("La API de Google está funcionando correctamente con los atributos del Libro.");
    }

}
