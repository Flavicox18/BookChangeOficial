package pe.edu.upao.bookchange.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import pe.edu.upao.bookchange.controller.IntercambioController;
import pe.edu.upao.bookchange.entity.Intercambio;
import pe.edu.upao.bookchange.service.IntercambioService;

@ExtendWith(MockitoExtension.class)
public class IntercambioServiceTest {

    @InjectMocks
    private IntercambioController intercambioController;

    @Mock
    private IntercambioService intercambioService;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        bindingResult = new BeanPropertyBindingResult(null, "");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddIntercambio() {
        Intercambio intercambio = new Intercambio();
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        ResponseEntity<?> response = intercambioController.addIntercambio(intercambio, bindingResult);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testAceptarIntercambio() {
        Integer intercambioId = 1;
        // Utiliza lenient para stubbing innecesario
        Mockito.lenient().doNothing().when(intercambioService).aceptarIntercambio(intercambioId);
        ResponseEntity<?> response = intercambioController.aceptarIntercambio(intercambioId);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testRechazarIntercambio() {
        Integer intercambioId = 1;
        // Utiliza lenient para stubbing innecesario
        Mockito.lenient().doNothing().when(intercambioService).rechazarIntercambio(intercambioId);
        ResponseEntity<?> response = intercambioController.rechazarIntercambio(intercambioId);
        assert response.getStatusCode() == HttpStatus.OK;
    }

}
