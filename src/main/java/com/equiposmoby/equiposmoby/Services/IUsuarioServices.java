package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.validation.BindingResult;

import javax.naming.ldap.HasControls;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUsuarioServices {

    List<User> traerTodas();
    boolean agregar(User usuario);
    User buscar(String txt, String password);
    void crearUsuario(String email , String password, HashMap<String, String> errores);
}
