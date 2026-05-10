package org.tuappnayra.controller;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.tuappnayra.model.Ave;
import org.tuappnayra.service.AveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import  static org.mockito.Mockito.when;
import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AveController.class)
public  class  AveControllerTest {

    @Autowired
    private  MockMvc  mockMvc;

    @MockitoBean
    private AveService service;

    @Test
    void  testListarTodosEndpoint() throws  Exception {
        Ave  ave = new  Ave();
        ave.setNombre("Guacamayo");

        when(service.obtenerTodas()).thenReturn(List.of(ave));
        mockMvc.perform(get("/api/aves"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("Guacamayo"));
    }
}
