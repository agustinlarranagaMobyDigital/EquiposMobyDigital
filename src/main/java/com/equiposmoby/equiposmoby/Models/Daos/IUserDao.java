package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.User;

import java.util.List;

public interface IUserDao<U, I extends Number> {

    public List<User> obtenerLista();

    public List<User> guardar(User newUser);
    /*
    public List<User> buscar();
    public List<User> eliminar();*/

}
