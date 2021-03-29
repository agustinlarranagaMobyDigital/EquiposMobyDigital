package com.equiposmoby.equiposmoby;

import com.equiposmoby.equiposmoby.Controllers.IntegranteController;
import com.equiposmoby.equiposmoby.Models.Daos.*;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import com.equiposmoby.equiposmoby.Services.EquipoServiceIMP;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class IntegranteTest {

    @Mock
    PuestoDAO puestoDAO;

    @Mock
    AgendaDAO agendaDAO;

    @Mock
    LenguajeDAO lenguajeDAO;

    @Mock
    Lenguaje lenguaje;

    @Mock
    EquipoDAO equipoDAO;

    @Mock
    IntegranteDAO integranteDAO;

    @Mock
    IDao iDao;

    @InjectMocks
    IntegranteController controller;

    @InjectMocks
    IntegranteService service;




    @Test
    void getEquipoByID_TestEXITOSO(){
        //given
        Equipo equipo = new Equipo(1,"trainies");
        when(equipoDAO.traerTodas()).thenReturn(DatosIntegrante.EQUIPOS);
        //when
        Equipo buscado =service.getEquipoByID(1);
        //then
        assertNotNull(buscado);
        assertEquals(equipo,buscado);
    }

    @Test
    void getEquipoByID_Test_EQUIPONULL(){
        //given
        Equipo equipo = mock(Equipo.class);
        when(equipoDAO.traerTodas()).thenReturn(DatosIntegrante.EQUIPOS);
        //when
        Equipo buscado =service.getEquipoByID(100);
        //then
        assertNull(buscado);
        assertNotEquals(equipo,buscado);
    }

    @Test
    void getEquipoByID_Test_IDMAL(){
        //given
        Equipo equipo = mock(Equipo.class);
        //when
        Equipo buscado =service.getEquipoByID(-1);
        //then
        assertNull(buscado);
        assertNotEquals(equipo,buscado);
    }


    @Test
    void getPuestoByID_Test_EXITOSO(){
        //given
        Puesto puesto = new Puesto(3,"back");
        when(puestoDAO.traerTodas()).thenReturn(DatosIntegrante.PUESTOS);
        //when
        Puesto buscado =service.getPuestoByID(3);
        //then
        assertNotNull(buscado);
        assertEquals(puesto,buscado);
    }

    @Test
    void getPuestoByID_Test_PUESTONULL(){
        //given
        Puesto puesto = mock(Puesto.class);
        when(puestoDAO.traerTodas()).thenReturn(DatosIntegrante.PUESTOS);
        //when
        Puesto buscado =service.getPuestoByID(3000);
        //then
        assertNull(buscado);
        assertNotEquals(puesto,buscado);
    }

    @Test
    void getPuestoByID_Test_IDMAL(){
        //given
        Puesto puesto = mock(Puesto.class);
        //when
        Puesto buscado =service.getPuestoByID(-1);
        //then
        assertNull(buscado);
        assertNotEquals(puesto,buscado);
    }


   /*  @Test
    void agregarAgenda_Test_Exitoso(){
        //given
        Integrante integrante = mock(Integrante.class);
        Agenda agenda = mock(Agenda.class);
        doNothing().when(agendaDAO).agregar(isA(Agenda.class));
        //when
        service.agregarAgenda(integrante);
        //then
        assertNotNull(integrante.getAgenda());
    }


   @Test
    void eliminarController_Test_Exitoso(){
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        In

        controller.eliminar(model,1,session);
    }*/

}
