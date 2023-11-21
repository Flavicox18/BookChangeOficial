package pe.edu.upao.bookchange.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pe.edu.upao.bookchange.controller.LibroController;
import pe.edu.upao.bookchange.dto.LibroDto;
import pe.edu.upao.bookchange.entity.Libro;
import pe.edu.upao.bookchange.service.LibroService;


public class LibroControllerTest {

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

}
