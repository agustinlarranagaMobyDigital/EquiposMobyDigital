package com.equiposmoby.equiposmoby.Models.Daos;

import java.util.List;

public interface IDao<T,t>{

    List<T> traerTodas();
    void agregar(T t);
    void eliminar(T t);
    T buscar(String txt);
    T getById(Integer id);

}
