package com.equiposmoby.equiposmoby.controllers;

import com.equiposmoby.equiposmoby.Controllers.AgendaController;
import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Services.AgendaService;
import com.equiposmoby.equiposmoby.utils.FactoryObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AgendaControllerTest implements FactoryObject {

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    AgendaController agendaController;

    @Mock
    AgendaService agendaService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(agendaController).build();
    }

    @Test
    public void agregarEventoTest() throws Exception {

        String jsonRequest = createJsonRequest(createReunion());
        when(agendaService.agregar(1, createReunion())).thenReturn(createReunion());
        mockMvc.perform(post("/agenda/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    public void traerTodasTest() throws Exception {

        when(agendaService.traerAgendas()).thenReturn(cargarListDeAgenda());
        mockMvc.perform(get("/agenda/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void traerPorIdTest() throws Exception {

        when(agendaService.traerPorId(1)).thenReturn(createAgenda());
        MvcResult result = mockMvc.perform(get("/agenda/{id-agenda}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Agenda response = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Agenda.class);
        //convierte un json al objeto pasado en .class
        assertEquals(createAgenda(), response);
    }

    @Test
    public void eliminarReunionTest() throws Exception {

        when(agendaService.eliminarEvento(1, 1)).thenReturn(createReunion());
        MvcResult result = mockMvc.perform(delete("/agenda/{id-agenda}/{id-reunion}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Reunion response = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Reunion.class);
        assertEquals(createReunion(), response);
    }

    @Test
    public void eliminarAgendaTest() throws Exception {

        when(agendaService.eliminar(1)).thenReturn(createAgenda());
        MvcResult result = mockMvc.perform(delete("/agenda/{id-agenda}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Agenda response = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Agenda.class);
        assertEquals(createAgenda(), response);
    }
}
