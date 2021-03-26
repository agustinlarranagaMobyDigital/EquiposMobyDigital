package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface IUsuarioServices {

    List<User> traerTodas();
    boolean agregar(User usuario);
    User buscar(String txt, String password);
    Map<String , String> crearUsuario(BindingResult result , String email ,  String password);
}
