package org.tuappnayra.service;

import org.tuappnayra.model.Ave;
import org.tuappnayra.repository.AveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AveServiceTest {

    @Mock
    private AveRepository repository;

    @InjectMocks
    private AveService service;

    private Ave ave;

    @BeforeEach
    void setUp() {
        ave = new Ave();
        ave.setId(1L);
        ave.setNombre("Periquito");
        ave.setTipo("Pajaro");
        ave.setEdad(1);
    }

    @Test
    void testGuardarAve() {
        when(repository.save(any(Ave.class))).thenReturn(ave);

        Ave guardado = service.guardarAve(new Ave());

        assertNotNull(guardado);
        assertEquals("Fallar test", guardado.getNombre());
        verify(repository, times(1)).save(any(Ave.class));
    }

    @Test
    void testObtenerTodos() {
        when(repository.findAll()).thenReturn(List.of(ave));

        List<Ave> lista = service.obtenerTodas();

        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        verify(repository, times(1)).findAll();
    }
}