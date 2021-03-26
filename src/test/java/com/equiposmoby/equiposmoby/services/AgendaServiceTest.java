package com.equiposmoby.equiposmoby.services;

import com.equiposmoby.equiposmoby.Models.Daos.AgendaDAO;
import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Services.AgendaService;
import com.equiposmoby.equiposmoby.excepciones.AgendaNoEncontradaException;
import com.equiposmoby.equiposmoby.excepciones.ReunionNoEncontradaException;
import com.equiposmoby.equiposmoby.utils.FactoryObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class AgendaServiceTest implements FactoryObject {

    @InjectMocks
    AgendaService agendaService;

    @Mock
    AgendaDAO agendaDAO;

    @Mock
    ReunionDaoImple reunionDaoImple;

    @Before
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void agregarTest() {

        Agenda agenda = createAgenda();
        Reunion reunion = createReunion();
        when(agendaDAO.buscarPorId(1)).thenReturn(Optional.ofNullable(agenda));
        Reunion reunion1 = agendaService.agregar(agenda.getIdAgenda(), reunion);
        assertEquals(reunion, reunion1);
    }

    @Test
    public void eliminarEventoTest() {

        when(agendaDAO.buscarPorId(1)).thenReturn(Optional.ofNullable(createAgenda()));
        when(reunionDaoImple.traerReunionPorId(1)).thenReturn(Optional.ofNullable(createReunion()));
        List<Reunion> reunionList = cargarListDeReunion();
        when(agendaDAO.modificar(1, reunionList)).thenReturn(createAgenda());
        Reunion reunion = agendaService.eliminarEvento(1, 1);
        assertEquals(reunionList.get(0), reunion);
    }

    @Test
    public void eliminarTest() {

        when(agendaDAO.buscarPorId(1)).thenReturn(Optional.ofNullable(createAgenda()));
        doNothing().when(agendaDAO).eliminar(createAgenda());
        Agenda agenda = agendaService.eliminar(createAgenda().getIdAgenda());
        assertEquals(createAgenda(), agenda);
    }

    @Test
    public void traerAgendasTest() {

        when(agendaDAO.traerTodas()).thenReturn(cargarListDeAgenda());
        List<Agenda> agendas = agendaService.traerAgendas();
        assertEquals(cargarListDeAgenda(), agendas);
    }

    @Test
    public void traerPorIdTest() {

        when(agendaDAO.getById(1)).thenReturn(createAgenda());
        Agenda agenda = agendaService.traerPorId(createAgenda().getIdAgenda());
        assertEquals(createAgenda(), agenda);
    }

    @Test(expected = AgendaNoEncontradaException.class)
    public void AgendaNoEncontradaExceptionTest() {

        when(agendaDAO.buscarPorId(7)).thenReturn(Optional.ofNullable(null));
        agendaService.agregar(7, createReunion());
    }

    @Test(expected = ReunionNoEncontradaException.class)
    public void ReunionNoEncontradaTest() {

        when(agendaDAO.buscarPorId(1)).thenReturn(Optional.ofNullable(createAgenda()));
        when(reunionDaoImple.traerReunionPorId(1)).thenReturn(Optional.ofNullable(null));
        when(agendaDAO.modificar(1, cargarListDeReunion())).thenReturn(createAgenda());
        agendaService.eliminarEvento(1, 1);
    }
}