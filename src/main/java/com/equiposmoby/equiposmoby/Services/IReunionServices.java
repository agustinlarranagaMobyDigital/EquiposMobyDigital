package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.Reunion;

import java.util.List;

public interface IReunionServices {

     List<Reunion> traerTodas();
     boolean agregar(Reunion reunion);
     boolean eliminar(Reunion reunion);
     Reunion buscar(String txt);

}
