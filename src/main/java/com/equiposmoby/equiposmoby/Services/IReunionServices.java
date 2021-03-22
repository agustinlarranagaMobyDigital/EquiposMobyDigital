package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.Reunion;

import java.util.List;

public interface IReunionServices {

     List<Reunion> traerTodas();
     void agregar(Reunion reunion);
     void eliminar(Reunion reunion);
     Reunion buscar(String txt);

}
