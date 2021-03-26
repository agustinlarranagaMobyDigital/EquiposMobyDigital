package com.equiposmoby.equiposmoby.Controllers;


import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.SessionService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration
@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    MockMvc mockMvc;

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
        String jsonUser = new Gson().toJson(buscado);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        Map<String, String> errores = new HashMap<>();
        when(sessionService.crearSesion(buscado.getEmail(),buscado.getPassword(),httpServletRequest)).thenReturn(Collections.emptyMap());
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonUser)).andExpect(status().isOk());
    }


}