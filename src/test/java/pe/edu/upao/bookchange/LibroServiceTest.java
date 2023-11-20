package pe.edu.upao.bookchange;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upao.bookchange.dto.LibroDto;
import pe.edu.upao.bookchange.entity.Libro;
import pe.edu.upao.bookchange.repository.LibroRepository;
import pe.edu.upao.bookchange.service.LibroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @Test
    public void testDetallesLibro() {
        // Mock de un libro
        Libro libroMock = new Libro();
        libroMock.setIdLibro(1L);
        libroMock.setNombre("El Gran Libro");
        libroMock.setAutor("Autor Anónimo");
        libroMock.setEstado("disponible");

        // Configuración del comportamiento del repositorio
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libroMock));

        // Prueba del método obtenerDetallesLibro
        LibroDto libroDto = libroService.detallesLibro(1L);

        // Verificación de resultados
        assertNotNull(libroDto);
        assertEquals(1L, libroDto.getIdLibro());
        assertEquals("El Gran Libro", libroDto.getNombre());
        assertEquals("Autor Anónimo", libroDto.getAutor());
        assertEquals("disponible", libroDto.getEstado());
    }

    @Test
    public void testListarLibrosDisponibles() {
        // Mock de varios libros disponibles
        Libro libro1 = new Libro();
        libro1.setIdLibro(1L);
        libro1.setNombre("Libro 1");
        libro1.setAutor("Autor 1");
        libro1.setEstado("disponible");

        Libro libro2 = new Libro();
        libro2.setIdLibro(2L);
        libro2.setNombre("Libro 2");
        libro2.setAutor("Autor 2");
        libro2.setEstado("disponible");

        List<Libro> librosDisponibles = new ArrayList<>();
        librosDisponibles.add(libro1);
        librosDisponibles.add(libro2);

        // Configuración del comportamiento del repositorio
        when(libroRepository.findByEstado("disponible")).thenReturn(librosDisponibles);

        // Prueba del método listarLibrosDisponibles
        List<LibroDto> librosDto = libroService.listarLibrosDisponibles();

        // Verificación de resultados
        assertNotNull(librosDto);
        assertEquals(2, librosDto.size());
        assertEquals(1L, librosDto.get(0).getIdLibro());
        assertEquals(2L, librosDto.get(1).getIdLibro());
        // Verifica otros atributos si es necesario
    }

    @Test
    public void testDetallesLibro_NoEncontrado() {
        // Configuración del comportamiento del repositorio para devolver un libro vacío
        when(libroRepository.findById(1L)).thenReturn(Optional.empty());

        // Prueba del método obtenerDetallesLibro
        LibroDto libroDto = libroService.detallesLibro(1L);

        // Verificación de resultados
        assertNull(libroDto);
    }
}