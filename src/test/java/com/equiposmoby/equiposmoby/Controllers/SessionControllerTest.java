package com.equiposmoby.equiposmoby.Controllers;


import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration
@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    SessionController sessionController;

    @MockBean
    SessionService sessionService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
       // mockMvc = MockMvcBuilders.standaloneSetup(SessionController.class);
    }

    @Test
    public void loginTest() throws Exception {
        User buscado = new User();
        buscado.setEmail("correo@Correo");
        buscado.setPassword("password");
        //String jsonUser = new Gson().toJson(buscado);
        Model model = mock(Model.class);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(sessionService.crearSesion(buscado,httpServletRequest)).thenReturn(Collections.emptyMap());
        //mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonUser)).andExpect(status().isOk());
        String resultado = sessionController.login(buscado,httpServletRequest,model);
        assertEquals(resultado , "redirect:/app");
    }

    /**/
    @Test
    public void login_email_vacio_Test() throws Exception {
        User buscado = new User();
        buscado.setEmail("");
        buscado.setPassword("password");
        Model model = mock(Model.class);
        //String jsonUser = new ObjectMapper().writeValueAsString(buscado);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        Map<String, String> errores = new HashMap<>();
        errores.put("email" , "email vacio");
        when(sessionService.crearSesion(buscado,httpServletRequest)).thenReturn(errores);
        //mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).accept(jsonUser)).andExpect(status().isBadRequest());
        String resultado = sessionController.login(buscado,httpServletRequest,model);
        assertEquals(resultado , "login");

    }

    @Test
    public void login_password_vacio_Test() throws Exception {
        User buscado = new User();
        buscado.setEmail("correo@Correo");
        buscado.setPassword("");
        Model model = mock(Model.class);
        //String jsonUser = new ObjectMapper().writeValueAsString(buscado);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        Map<String, String> errores = new HashMap<>();
        errores.put("password" , "password vacio");
        when(sessionService.crearSesion(buscado,httpServletRequest)).thenReturn(errores);
        //mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).accept(jsonUser)).andExpect(status().isBadRequest());
        String resultado = sessionController.login(buscado,httpServletRequest,model);
        assertEquals(resultado , "login");

    }

    @Test
    public void login_usuario_incorrecto_Test() throws Exception {
        User buscado = new User();
        buscado.setEmail("correo@Correo");
        buscado.setPassword("password");
        User nuevo = new User();
        buscado.setEmail("otroCorreo@correo.com");
        buscado.setPassword("password");
        Model model = mock(Model.class);
        //String jsonUser = new ObjectMapper().writeValueAsString(buscado);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        Map<String, String> errores = new HashMap<>();
        errores.put("usuario" , "no existe el usuario");
        when(sessionService.crearSesion(buscado,httpServletRequest)).thenReturn(errores);
        //mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).accept(jsonUser)).andExpect(status().isBadRequest());
        String resultado = sessionController.login(buscado,httpServletRequest,model);
        assertEquals(resultado , "login");

    }


}