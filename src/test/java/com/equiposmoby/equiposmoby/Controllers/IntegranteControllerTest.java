package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.*;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import com.equiposmoby.equiposmoby.Services.SessionService;
import com.equiposmoby.equiposmoby.Services.UsuarioServiceIMP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IntegranteControllerTest {

    BindingResult result ;

    Model model;

    HttpSession session;

    @InjectMocks
    IntegranteController integranteController;

    @Mock
    IntegranteService integranteService;

    @Mock
    UsuarioServiceIMP usuarioServiceIMP;

    @Mock
    SessionService sessionService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        result = mock(BindingResult.class);
        session = mock(HttpSession.class);
        model = mock(Model.class);
    }


    @Test
    public void guardarIntegranteTest(){
        Integrante integrante = new Integrante();
        integrante.setNombre("isaias");
        integrante.setApellido("calfin");
        integrante.setFechaNacimiento(LocalDate.of(1999,8,22));
        integrante.setExperiencia(2);
        integrante.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        integrante.setPuesto(Puesto.builder().nombre("front-end").build());
        integrante.setLenguajes(new ArrayList<Lenguaje>());
        integrante.setUsuario(new User("isaias@emal","password"));
        when(usuarioServiceIMP.crearUsuario(result , "correo@Correo" , "password")).thenReturn(Collections.emptyMap());
        when(usuarioServiceIMP.getUsuarioByEmail("correo@Correo")).thenReturn(mock(User.class));
        when(integranteService.add(integrante)).thenReturn(true);
        when(sessionService.sesionIniciada(session , "redirect:/listaIntegrantes")).thenReturn("redirect:/listaIntegrantes");
        String resultado = integranteController.addIntegrante(integrante , result , model , "correo@Correo" , "password" , session);
        assertEquals("redirect:/listaIntegrantes" , resultado);
    }
    @Test
    public void guardar_integrante_Nombre_vacio_Test(){
        Integrante integrante = new Integrante();
        integrante.setNombre("");
        integrante.setApellido("calfin");
        integrante.setFechaNacimiento(LocalDate.of(1999,8,22));
        integrante.setExperiencia(2);
        integrante.setAgenda(Agenda.builder().reuniones(new ArrayList<Reunion>()).build());
        integrante.setPuesto(Puesto.builder().nombre("front-end").build());
        integrante.setLenguajes(new ArrayList<Lenguaje>());
        integrante.setUsuario(new User("isaias@emal","password"));
        Map<String , String> errores = new HashMap<>();
        errores.put("nombre" ,  "nombre , vacio");
        when(usuarioServiceIMP.crearUsuario(result , "correo@Correo" , "password")).thenReturn(errores);
        when(usuarioServiceIMP.getUsuarioByEmail("correo@Correo")).thenReturn(null);
        when(sessionService.sesionIniciada(session , "redirect:/listaIntegrantes")).thenReturn("redirect:/listaIntegrantes");
        when(integranteService.add(integrante)).thenReturn(false);
        String resultado = integranteController.addIntegrante(integrante , result , model , "correo@Correo" , "password" , session);
        assertNotEquals("redirect:/listaIntegrantes" , resultado);
    }

    @Test
    public void editar_test(){
        when(integranteService.getById(2)).thenReturn(mock(Integrante.class));
        when(sessionService.sesionIniciada(session , "formIntegrante")).thenReturn("formIntegrante");
        String resultado = integranteController.editar(model , 2 , session);
        assertEquals("formIntegrante" , resultado);

    }

    @Test
    public void editar_Id_nulo_test(){
        when(integranteService.getById(2)).thenReturn(mock(Integrante.class));
        when(sessionService.sesionIniciada(session , "formIntegrante")).thenReturn("formIntegrante");
        String resultado = integranteController.editar(model , 0 , session);
        assertNotEquals("formIntegrante" , resultado);

    }

    @Test
    public void editar_integrante_no_encontrado_test(){
        when(integranteService.getById(2)).thenReturn(mock(Integrante.class));
        when(sessionService.sesionIniciada(session , "formIntegrante")).thenReturn("formIntegrante");
        String resultado = integranteController.editar(model , 3 , session);
        assertNotEquals("formIntegrante" , resultado);

    }

    @Test
    public void verAgendaPersona_test(){
        when(integranteService.getById(2)).thenReturn(mock(Integrante.class));
        when(sessionService.sesionIniciada(session , "verAgenda")).thenReturn("verAgenda");
        String resultado = integranteController.verAgendaPersonal(model , session , 2);
        assertEquals("verAgenda" , resultado);
    }

    @Test
    public void verAgendaPersona_Id_nulo_test(){
        when(integranteService.getById(2)).thenReturn(mock(Integrante.class));
        when(sessionService.sesionIniciada(session , "verAgenda")).thenReturn("verAgenda");
        String resultado = integranteController.verAgendaPersonal(model , session , 0);
        assertNotEquals("verAgenda" , resultado);
    }

    @Test
    public void verAgendaPersona_Integrante_no_encontrado_test(){
        when(integranteService.getById(2)).thenReturn(mock(Integrante.class));
        when(sessionService.sesionIniciada(session , "verAgenda")).thenReturn("verAgenda");
        String resultado = integranteController.verAgendaPersonal(model , session , 0);
        assertNotEquals("verAgenda" , resultado);
    }
}