package pe.edu.upao.bookchange;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pe.edu.upao.bookchange.controller.IntercambioController;
import pe.edu.upao.bookchange.entity.Intercambio;
import pe.edu.upao.bookchange.service.IntercambioService;

import java.util.List;

@SpringBootTest
class BookChangeApplicationTests {

    @InjectMocks
    private IntercambioController intercambioController;

    @Mock
    private IntercambioService intercambioService;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void testAddIntercambio() {

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Intercambio intercambio = new Intercambio();

        ResponseEntity<?> response = intercambioController.addIntercambio(intercambio, bindingResult);

        assert response.getStatusCode() == HttpStatus.OK;

    }

    @Test
    public void testAceptarIntercambio() {

        Integer intercambioId = 1;


        Mockito.doNothing().when(intercambioService).aceptarIntercambio(intercambioId);

        ResponseEntity<?> response = intercambioController.aceptarIntercambio(intercambioId);

        assert response.getStatusCode() == HttpStatus.OK;

    }

    @Test
    public void testRechazarIntercambio() {

        Integer intercambioId = 1;


        Mockito.doNothing().when(intercambioService).rechazarIntercambio(intercambioId);

        ResponseEntity<?> response = intercambioController.rechazarIntercambio(intercambioId);

        assert response.getStatusCode() == HttpStatus.OK;

    }

}
