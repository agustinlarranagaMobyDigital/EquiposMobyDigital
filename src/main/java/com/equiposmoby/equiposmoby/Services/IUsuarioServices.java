package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.User;

import java.util.List;

public interface IUsuarioServices {
    List<User> traerTodas();
    void agregar(User usuario);
    void eliminar(User usuario);
    User buscar(String txt, String password);
}
