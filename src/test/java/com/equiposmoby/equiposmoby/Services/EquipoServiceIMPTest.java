package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Models.Entity.Lider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class EquipoServiceIMPTest {

    @Test
    void testAgregar() {
        Equipo equipo = new Equipo();
        equipo.setNombre("Juan Perez");
        equipo.setLider(new Lider());
        equipo.setArrayList(new ArrayList<>());



    }

    @Test
    void testEliminar() {
    }

    @Test
    void testBuscar() {
    }
}