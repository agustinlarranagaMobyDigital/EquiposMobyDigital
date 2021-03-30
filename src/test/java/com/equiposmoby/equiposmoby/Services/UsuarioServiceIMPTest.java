package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.UserDao;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class UsuarioServiceIMPTest{

    @InjectMocks
    private UsuarioServiceIMP usuarioServiceIMP;

    @Mock
    private UserDao userDao;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    /**
     * Agregar Usuario Test
     **/
    @Test
    public void agregarUsuarioTest(){
        User nuevo = new User();
        nuevo.setEmail("usuario@email");
        nuevo.setPassword("contraseña");
        doNothing().when(userDao).agregar(isA(User.class));
        boolean resultado = usuarioServiceIMP.agregar(nuevo);
        assertTrue(resultado);
    }

    @Test
    public void agregarUsuarioPasswordVacioTest(){
        User nuevo = new User();
        nuevo.setEmail("usuario@email");
        doNothing().when(userDao).agregar(isA(User.class));
        boolean resultado = usuarioServiceIMP.agregar(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregarUsuarioEmailVacioTest(){
        User nuevo = new User();
        nuevo.setPassword("contraseña");
        doNothing().when(userDao).agregar(isA(User.class));
        boolean resultado = usuarioServiceIMP.agregar(nuevo);
        assertFalse(resultado);
    }

    @Test
    public void agregarUsuarioUsuarioVacioTest(){
        User nuevo = new User();
        doNothing().when(userDao).agregar(isA(User.class));
        boolean resultado = usuarioServiceIMP.agregar(nuevo);
        assertFalse(resultado);
    }


    /**
     * Buscar Usuario Test
     **/

    @Test
    public void buscarTeste(){
        User usuario = new User();
        usuario.setEmail("usuario@email");
        usuario.setPassword("isaias");
        when(userDao.buscar(usuario.getEmail())).thenReturn(usuario);
        User buscado = (User) usuarioServiceIMP.buscar("usuario@email", "isaias");
        assertEquals(usuario , buscado);
    }

    @Test
    public void buscarEmailIncorrectoTeste(){
        User usuario = new User();
        usuario.setEmail("usuario@email");
        usuario.setPassword("otraContraseña");
        when(userDao.buscar(usuario.getEmail())).thenReturn(usuario);
        User buscado = (User) usuarioServiceIMP.buscar("otrmail", "isaias");
        assertNull(buscado);
    }

    @Test
    public void buscarContraseniaIncorrectaTeste(){
        User usuario = new User();
        usuario.setEmail("usuario@email");
        usuario.setPassword("isaias");
        when(userDao.buscar(usuario.getEmail())).thenReturn(usuario);
        User buscado = (User) usuarioServiceIMP.buscar("usuario@email", "otracontraseña");
        assertNull(buscado);
    }

    @Test
    public void buscarContraseniaVaciaTeste(){
        User usuario = new User();
        usuario.setEmail("usuario@email");
        usuario.setPassword("isaias");
        when(userDao.buscar(usuario.getEmail())).thenReturn(usuario);
        User buscado = (User) usuarioServiceIMP.buscar("usuario@email", "");
        assertNull(buscado);
    }

    @Test
    public void buscarEmailVacioTeste(){
        User usuario = new User();
        usuario.setEmail("usuario@email");
        usuario.setPassword("isaias");
        when(userDao.buscar(usuario.getEmail())).thenReturn(usuario);
        User buscado = (User) usuarioServiceIMP.buscar("", "isaias");
        assertNull(buscado);
    }

    @Test
    public void buscarParametroVacioTeste(){
        User usuario = new User();
        usuario.setEmail("usuario@email");
        usuario.setPassword("isaias");
        when(userDao.buscar(usuario.getEmail())).thenReturn(usuario);
        User buscado = (User) usuarioServiceIMP.buscar("", "");
        assertNull(buscado);
    }


    @Test
    public void crearUsuarioTest(){
        Map<String , String> errores = new HashMap<>();
        BindingResult result = mock(BindingResult.class);
        when(userDao.buscar("correo@Correo")).thenReturn(null);
        doNothing().when(userDao).agregar(isA(User.class));
        //usuarioServiceIMP.crearUsuario( result, "correo@Correo" , "usuario",errores);
        assertTrue(errores.isEmpty());
    }

    @Test
    public void crear_Usuario_Email_Vacio_Test(){
        Map<String , String> errores = new HashMap<>();
        BindingResult result = mock(BindingResult.class);
        when(userDao.buscar("correo@Correo")).thenReturn(null);
        doNothing().when(userDao).agregar(isA(User.class));
        //Map<String , String> errores = usuarioServiceIMP.crearUsuario(  "" , "usuario",errores);
        assertFalse(errores.isEmpty());
    }

    @Test
    public void crear_Usuario_Password_Vacio_Test(){
        HashMap<String , String> errores = new HashMap<>();
        BindingResult result = mock(BindingResult.class);
        when(userDao.buscar("correo@Correo")).thenReturn(null);
        doNothing().when(userDao).agregar(isA(User.class));
        usuarioServiceIMP.crearUsuario( "correo@Correo" , "",errores);
        assertFalse(errores.isEmpty());
    }

    @Test
    public void crear_Usuario_Existe_Usuario_Test(){
        HashMap<String , String> errores = new HashMap<>();
        BindingResult result = mock(BindingResult.class);
        User creado = mock(User.class);
        when(userDao.buscar("correo@Correo")).thenReturn(creado);
        doNothing().when(userDao).agregar(isA(User.class));
        usuarioServiceIMP.crearUsuario("correo@Correo" , "",errores);
        assertFalse(errores.isEmpty());
    }

    @Test
    public void buscarByEmail(){
        User bucado = mock(User.class);
        when(userDao.buscar("correo@Correo")).thenReturn(bucado);
        User encontrado = usuarioServiceIMP.getUsuarioByEmail("correo@Correo");
        assertEquals(bucado , encontrado);
        assertNotNull(encontrado);
    }

    @Test
    public void buscarByEmail_email_vacio(){
        User bucado = mock(User.class);
        when(userDao.buscar("correo@Correo")).thenReturn(bucado);
        User encontrado = usuarioServiceIMP.getUsuarioByEmail("");
        assertNotEquals(bucado , encontrado);
        assertNull(encontrado);
    }

    @Test
    public void buscarById_Usuario_No_Encontrado(){
        User bucado = mock(User.class);
        when(userDao.buscar("correo@Correo")).thenReturn(null);
        User encontrado = usuarioServiceIMP.getUsuarioByEmail("correo@Correo");
        assertNotEquals(bucado , encontrado);
        assertNull(encontrado);
    }


}