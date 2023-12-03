package pe.edu.upao.bookchange.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pe.edu.upao.bookchange.controller.LibroController;
import pe.edu.upao.bookchange.dto.LibroDto;
import pe.edu.upao.bookchange.entity.Genero;
import pe.edu.upao.bookchange.entity.Libro;
import pe.edu.upao.bookchange.repository.LibroRepository;
import pe.edu.upao.bookchange.service.LibroService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testDetallesLibro() {
        // Mock de un libro
        Libro libroMock = new Libro();
        libroMock.setIdLibro(1L);
        libroMock.setNombre("El Gran Libro");
        libroMock.setAutor("Autor Anónimo");
        libroMock.setEstado("disponible");
        libroMock.setEditorial("Santillana");
        libroMock.setFoto("ola.png");
        libroMock.setSinopsis("Era una vez...");
        libroMock.setIsbn(9137317923463L);

        List<Genero> generos = new ArrayList<>();
        Genero accion = new Genero();
        accion.setNombreGenero("Acción");
        generos.add(accion);

        // Crear una fecha de lanzamiento
        Date fechaLanzamiento = new Date();

        libroMock.setFechaLanzamiento(fechaLanzamiento);
        libroMock.setGenero(generos);

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
        assertEquals("Santillana", libroDto.getEditorial());
        assertEquals("ola.png", libroDto.getFoto());
        assertEquals("Era una vez...", libroDto.getSinopsis());
        assertEquals(9137317923463L, libroDto.getIsbn());

        assertNotNull(libroDto.getGenero());
        assertFalse(libroDto.getGenero().isEmpty());
        assertEquals("Acción", libroDto.getGenero().get(0).getNombreGenero());

        assertNotNull(libroDto.getFechaLanzamiento());
        assertEquals(fechaLanzamiento, libroDto.getFechaLanzamiento());
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
    }

    @Test
    public void testDetallesLibro_NoEncontrado() {
        // Configuración del comportamiento del repositorio para devolver un libro vacío
        when(libroRepository.findById(1L)).thenReturn(Optional.empty());
        LibroDto libroDto = libroService.detallesLibro(1L);
        assertNull(libroDto);
    }

    @Test
    public void testBuscarLibroPorTituloOAutor() {
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

        List<Libro> librosEncontrados = new ArrayList<>();
        librosEncontrados.add(libro1);
        librosEncontrados.add(libro2);

        // Configuración del comportamiento del repositorio
        when(libroRepository.findByNombreContainingIgnoreCaseOrAutorContainingIgnoreCase(anyString(), anyString()))
                .thenReturn(librosEncontrados);

        // Prueba del método buscarLibroPorTituloOAutor
        List<LibroDto> librosDto = libroService.buscarLibroPorTituloOAutor("Libro");

        // Verificación de resultados
        assertNotNull(librosDto);
        assertEquals(2, librosDto.size());
    }

    @Test
    public void testEditarLibro() {
        // Arrange
        Long idLibro = 1L;
        LibroDto libroDto = new LibroDto(/* provide necessary values */);
        BindingResult bindingResult = mock(BindingResult.class);

        LibroService libroService = mock(LibroService.class);
        LibroController libroController = new LibroController(libroService);

        Libro libroExistente = new Libro(/* provide necessary values */);
        when(libroService.findById(idLibro)).thenReturn(libroExistente);

        // Act
        ResponseEntity<?> responseEntity = libroController.editarLibro(idLibro, libroDto, bindingResult);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Libro Actualizado", responseEntity.getBody());

        // Verifica que se haya llamado al método editarLibro de libroService
        ArgumentCaptor<Libro> libroCaptor = ArgumentCaptor.forClass(Libro.class);
        verify(libroService).editarLibro(libroCaptor.capture());

        // Verifica que el libroExistente se haya actualizado con los valores de libroDto
        Libro libroActualizado = libroCaptor.getValue();
        assertEquals(libroDto.getNombre(), libroActualizado.getNombre());
        assertEquals(libroDto.getAutor(), libroActualizado.getAutor());
        // Agrega más aserciones según los campos que tengas en Libro y LibroDto

    }

    @Test
    public void testEliminarLibroExistente() {
        Long idLibro = 1L;
        Libro libroExistente = new Libro();
        libroExistente.setIdLibro(idLibro);

        when(libroRepository.findById(idLibro)).thenReturn(Optional.of(libroExistente));
        ResponseEntity<?> responseEntity = libroService.eliminarLibro(idLibro);

        verify(libroRepository).findById(idLibro);
        verify(libroRepository).deleteById(idLibro);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("Libro Eliminado"), responseEntity);
    }
}
