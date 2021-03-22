package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.Equipo;

import java.util.List;

public interface IEquipoService {

    List<Equipo> traerTodas();
    void agregar(Equipo equipo);
    void eliminar(Equipo equipo);
    Equipo buscar(String nombre);

}
