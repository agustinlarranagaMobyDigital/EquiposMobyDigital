package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.CuentaDAO;
import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;
import com.equiposmoby.equiposmoby.utils.FactoryObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CuentaServiceTest implements FactoryObject {

    @InjectMocks
    CuentaService cuentaService;

    @Mock
    CuentaDAO cuentaDAO;

    @Before
    public void setUp(){

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void traerTodasTest(){

        when(cuentaDAO.traerTodas()).thenReturn(cargarListCuenta());
        List<Cuenta> cuentas = cuentaService.traerTodas();
        assertEquals(cargarListCuenta(), cuentas);
    }

    @Test
    public void agregarTest(){

        doNothing().when(cuentaDAO).agregar(isA(Cuenta.class));
        cuentaService.agregar(createCuenta());
    }

    @Test
    public void eliminarTest(){

        doNothing().when(cuentaDAO).eliminar(isA(Cuenta.class));
        cuentaService.eliminar(createCuenta());
    }

    @Test
    public void buscarTest(){

        when(cuentaDAO.buscar("Prisma")).thenReturn(createCuenta());
        Cuenta cuenta = cuentaService.buscar("Prisma");
        assertEquals(createCuenta(),cuenta);
    }

}
