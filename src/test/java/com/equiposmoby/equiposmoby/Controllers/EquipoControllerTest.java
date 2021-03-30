package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Services.EquipoServiceIMP;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EquipoControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    EquipoController equipoController;

    @Mock
    EquipoServiceIMP equipoServiceIMP;

    @Mock
    IntegranteService integranteService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(equipoController)
                .build();
    }

    @Test
    public void guardarEquipoTest() throws Exception {

        when(equipoServiceIMP.agregarAgenda(mock(Equipo.class))).thenReturn(true);
        doNothing().when(equipoServiceIMP).agregar(mock(Equipo.class));
        mockMvc.perform(post("/guardarEquipo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void listarEquipoTest() throws Exception {

        List<Equipo> listaEquipos = new ArrayList<>();
        listaEquipos.add(mock(Equipo.class));
        when(equipoServiceIMP.traerTodas()).thenReturn(listaEquipos);
        mockMvc.perform(get("/listarEquipos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void verAgendaEquipoTest() throws Exception{

        mockMvc.perform(get("/agendaEquipo/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void eliminarIntegranteTest() throws Exception{

        doNothing().when(integranteService).quitarEquipo(1);
        mockMvc.perform(delete("/eliminarIntegrante/{Eid}/{Iid}",1,1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void gestionarEquipoTest() throws Exception {

        List<Integrante> integrantes = new ArrayList<>();
        integrantes.add(mock(Integrante.class));
        when(equipoServiceIMP.getById(1)).thenReturn(mock(Equipo.class));
        when(integranteService.getOrderIntegrante()).thenReturn(integrantes);
        mockMvc.perform(get("/gestionarEquipo/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void agregarIntegranteTest() throws Exception{
        doNothing().when(integranteService).asignarEquipo(Integrante.builder().build(), Equipo.builder().id(1).build());
        mockMvc.perform(post("/agregarIntegrante/{Eid}/{Iid}",1,1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andReturn();
    }
}
