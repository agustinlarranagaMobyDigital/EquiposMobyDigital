package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.User;

import java.util.List;

public interface IUsuarioServices {
    List<User> traerTodas();
    boolean agregar(User usuario);
    void eliminar(String email);
    User buscar(String txt, String password);
}
