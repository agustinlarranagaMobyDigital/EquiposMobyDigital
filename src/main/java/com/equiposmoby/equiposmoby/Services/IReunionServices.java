package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.Reunion;

import java.util.List;

public interface IReunionServices {

    public List<Reunion> traerTodas();
    public void agregar(Reunion reunion);
    public void eliminar(Reunion reunion);
    public Reunion buscar(String txt);

}
