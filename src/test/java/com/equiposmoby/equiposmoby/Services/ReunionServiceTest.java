package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.excepciones.IdIncorrectoException;
import com.equiposmoby.equiposmoby.excepciones.ReunionNoEncontrada;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class ReunionServiceTest {

    @InjectMocks
    private ReunionService reunionService; ///La clase es el inject
    @Mock
    private ReunionDaoImple reunionDaoImple;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown(){
        reunionDaoImple = null;
    }

    ///----------------TEST-----------------

    /** AGREGAR */
    @Test
    public void agregarReunionTest(){
        //Reunion reunion = mock(Reunion.class); ///Agarra todos los test y evalua todos los que no hicieron
        Reunion reunion = new Reunion();

        reunion.setFecha(LocalDate.of(2021,6,15));
        reunion.setHoraInicial(LocalTime.of(8,45));
        reunion.setHoraFinal(LocalTime.of(9,00));
        ///Tipo reunion

        doNothing().when(reunionDaoImple).agregar(isA(Reunion.class));
        boolean resultado = reunionService.agregar(reunion);
        assertTrue(resultado);
    }

    @Test
    public void agregarFechaAntesTest(){
        Reunion reunion = new Reunion();
        ///assertThrows()

        reunion.setFecha(LocalDate.of(2020,5,15));
        reunion.setHoraInicial(LocalTime.of(8,45));
        reunion.setHoraFinal(LocalTime.of(9,00));

        doNothing().when(reunionDaoImple).agregar(isA(Reunion.class));
        boolean resultado = reunionService.agregar(reunion);
        assertFalse(resultado);
    }

    @Test
    public void agregarFechaIgualTest(){
        Reunion reunion = new Reunion();

        reunion.setFecha(LocalDate.now());
        reunion.setHoraInicial(LocalTime.of(8,45));
        reunion.setHoraFinal(LocalTime.of(9,00));

        doNothing().when(reunionDaoImple).agregar(isA(Reunion.class));
        boolean resultado = reunionService.agregar(reunion);
        assertTrue(resultado);
    }

    /** ELIMINAR */

    @Test
    public void eliminarReunionTest(){
        //Reunion reunion = mock(Reunion.class); ///Agarra todos los test y evalua todos los que no hicieron
        Reunion reunion = new Reunion();

        reunion.setFecha(LocalDate.of(2021,6,15));
        reunion.setHoraInicial(LocalTime.of(8,45));
        reunion.setHoraFinal(LocalTime.of(9,00));

        doNothing().when(reunionDaoImple).eliminar(isA(Reunion.class));
        boolean resultado = reunionService.eliminar(reunion);
        assertTrue(resultado);
    }

    @Test
    public void eliminarReunionMalFechaTest(){ ///Querer eliminar una reunion que ya se hizo-Fecha
        Reunion reunion = new Reunion();

        reunion.setFecha(LocalDate.of(2020,5,5));
        reunion.setHoraInicial(LocalTime.of(8,45));
        reunion.setHoraFinal(LocalTime.of(9,00));

        doNothing().when(reunionDaoImple).eliminar(isA(Reunion.class));
        boolean resultado = reunionService.eliminar(reunion);
        assertFalse(resultado);
    }

    /** BUSCAR */

    @Test
    public void buscarFeliz(){
        Reunion reunion = new Reunion();

        reunion.setFecha(LocalDate.of(2021,6,15));
        reunion.setHoraInicial(LocalTime.of(8,45));
        reunion.setHoraFinal(LocalTime.of(9,00));

        when(reunionDaoImple.getById(1)).thenReturn(reunion);
        Reunion resultado = reunionService.getById(1);
        assertEquals(reunion,resultado);
    }

    @Test
    public void buscarIdIncorrecto(){
        Reunion reunion = new Reunion();
        reunion.setIdReunion(1);
        reunion.setFecha(LocalDate.of(2021,6,15));
        reunion.setHoraInicial(LocalTime.of(8,45));
        reunion.setHoraFinal(LocalTime.of(9,00));

        when(reunionDaoImple.getById(1)).thenReturn(reunion);
        Reunion resultado = reunionService.getById(-55);
        assertNull(resultado);
    }


















}
