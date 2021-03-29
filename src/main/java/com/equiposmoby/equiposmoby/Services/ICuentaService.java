package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;

import java.util.List;

public interface ICuentaService {

    List<Cuenta> traerTodas();
    void agregar(Cuenta reunion);
    void eliminar(Cuenta reunion);
    Cuenta buscar(String txt);
    //-----------------------
    //Aca le damos comportamiento a cuenta en especifico, por ejemplo obtener los mayores a 10, unicamente se va a aplicar en Cuenta.

}
