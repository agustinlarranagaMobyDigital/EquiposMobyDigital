package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Services.CuentaService;
import com.equiposmoby.equiposmoby.utils.FactoryObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CuentaControllerTest implements FactoryObject {

    MockMvc mockMvc;

    @InjectMocks
    CuentaController cuentaController;

    @Mock
    CuentaService cuentaService;

    @Before
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cuentaController).build();
    }

    @Test
    public void guardarCuentaTest() throws Exception {

        doNothing().when(cuentaService).agregar(createCuenta());
        mockMvc.perform(post("/agregarCuenta")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}
