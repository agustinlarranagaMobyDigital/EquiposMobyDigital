package com.equiposmoby.equiposmoby;

import com.equiposmoby.equiposmoby.Controllers.IntegranteController;
import com.equiposmoby.equiposmoby.Models.Daos.EquipoDAO;
import com.equiposmoby.equiposmoby.Models.Daos.IntegranteDAO;
import com.equiposmoby.equiposmoby.Models.Daos.LenguajeDAO;
import com.equiposmoby.equiposmoby.Models.Daos.PuestoDAO;
import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
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
    LenguajeDAO lenguajeDAO;

    @Mock
    Lenguaje lenguaje;

    @Mock
    EquipoDAO equipoDAO;

    @Mock
    IntegranteDAO integranteDAO;

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
}
