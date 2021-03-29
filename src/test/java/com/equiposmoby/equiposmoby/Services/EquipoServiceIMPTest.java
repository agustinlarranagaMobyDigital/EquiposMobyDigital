package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.EquipoDAO;
import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.utils.FactoryObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class EquipoServiceIMPTest implements FactoryObject {

    @InjectMocks
    EquipoServiceIMP equipoServiceIMP;

    @Mock
    EquipoDAO equipoDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void traerTodasTest() {
        when(equipoDAO.traerTodas()).thenReturn(cargarListDeEquipo());
        List<Equipo> equipoAL = equipoServiceIMP.traerTodas();
        assertEquals(cargarListDeEquipo(), equipoAL);
    }

    @Test
    public void agregarTest() {
        doNothing().when(equipoDAO).agregar(createEquipo());
        equipoServiceIMP.agregar(createEquipo());
    }

    @Test
    public void eliminarTest() {
        doNothing().when(equipoDAO).eliminar(createEquipo());
        equipoServiceIMP.eliminar(createEquipo());
    }

    @Test
    public void buscarTest() {
        Equipo equipo = createEquipo();
        when(equipoDAO.buscar(createEquipo())).thenReturn(equipo);
        Equipo buscado = (Equipo) equipoServiceIMP.buscar(equipo.getNombre());
        assertEquals(equipo, buscado);
    }
}