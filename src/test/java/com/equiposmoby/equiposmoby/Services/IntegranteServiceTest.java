package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.*;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class IntegranteServiceTest {

    @InjectMocks
    private IntegranteService integranteService;

    @Mock
    private IntegranteDAO integranteDAO;

    @Mock
    private AgendaDAO agendaDAO;

    @Mock
    private PuestoDAO puestoDAO;

    @Mock
    private LenguajeDAO lenguajeDAO;

    @Mock
    private UserDao userDao;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void agregarIntegranteTest(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.of(1999,8,22));
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertTrue(resultado);
    }

    @Test
    public void agregar_Integrante_Nombre_Vacio_Test(){
        Integrante nuevo = new Integrante();

        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.of(1999,8,22));
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Apellido_Vacio_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setFechaNacimiento(LocalDate.of(1999,8,22));
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Nacimiento_Vacio_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Nacimiento_Hoy_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.now());
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Nacimiento_Superior_A_Hoy_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.now().plusDays(1));
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Experiencia_Vacio_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.of(1999,8,22));
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Agenda_Vacio_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.of(1999,8,22));
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Puesto_Vacio_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.of(1999,8,22));
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Lenguajes_Vacio_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.of(1999,8,22));
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setUsuario(new User("isaias@emal","password"));
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregar_Integrante_Usuario_Vacio_Test(){
        Integrante nuevo = new Integrante();
        nuevo.setNombre("isaias");
        nuevo.setApellido("calfin");
        nuevo.setFechaNacimiento(LocalDate.of(1999,8,22));
        nuevo.setExperiencia(2);
        nuevo.setJefe(false);
        nuevo.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        Puesto puesto = new Puesto();
        puesto.setNombre("frontend");
        nuevo.setPuesto(puesto);
        nuevo.setLenguajes(new ArrayList<Lenguaje>());
        boolean resultado = integranteService.add(nuevo);
        assertFalse(resultado);
    }

    /**
     Eliminar Integrante
     **/


    @Test
    public void Eliminar_Integrante_Test(){
        Integrante nuevo = new Integrante();
        User nuevoUsuario = new User("email" , "password");
        nuevo.setUsuario(nuevoUsuario);
        when(integranteService.getById(1)).thenReturn(nuevo);
        doNothing().when(integranteDAO).eliminar(isA(Integrante.class));
        doNothing().when(userDao).eliminar(isA(User.class));
        boolean resultado = integranteService.eliminar(1);
        assertTrue(resultado);
    }


    @Test
    public void Eliminar_Integrante_Id_0_Test(){
        Integrante nuevo = new Integrante();
        User nuevoUsuario = new User("email" , "password");
        nuevo.setUsuario(nuevoUsuario);
        when(integranteService.getById(1)).thenReturn(nuevo);
        doNothing().when(integranteDAO).eliminar(isA(Integrante.class));
        doNothing().when(userDao).eliminar(isA(User.class));
        boolean resultado = integranteService.eliminar(0);//parametro 0
        assertFalse(resultado);
    }

    @Test
    public void Eliminar_Integrante_No_Encuentra_integrante_Test(){
        Integrante nuevo = new Integrante();
        User nuevoUsuario = new User("email" , "password");
        nuevo.setUsuario(nuevoUsuario);
        when(integranteService.getById(1)).thenReturn(null);
        doNothing().when(integranteDAO).eliminar(isA(Integrante.class));
        doNothing().when(userDao).eliminar(isA(User.class));
        boolean resultado = integranteService.eliminar(1);
        assertFalse(resultado);
    }

    @Test
    public void get_Integrante_By_Id_Test(){
        Integrante integrante = mock(Integrante.class);
        when(integranteDAO.getById(2)).thenReturn(integrante);
        Integrante buscado = integranteService.getById(2);
        assertNotNull(buscado);
        assertEquals(buscado , integrante);
    }

    @Test
    public void get_Integrante_By_Id_No_Encuentra_Integrante_Test(){
        Integrante integrante = mock(Integrante.class);
        when(integranteDAO.getById(2)).thenReturn(integrante);
        Integrante buscado = integranteService.getById(1);
        assertNull(buscado);
        assertNotEquals(buscado , integrante);
    }

    @Test
    public void get_Integrante_By_Id_ID_vacio_Test(){
        Integrante integrante = mock(Integrante.class);
        when(integranteDAO.getById(2)).thenReturn(integrante);
        Integrante buscado = integranteService.getById(0);
        assertNull(buscado);
        assertNotEquals(buscado , integrante);
    }

}