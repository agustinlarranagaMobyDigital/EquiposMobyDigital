package com.equiposmoby.equiposmoby.excepciones;

public class FechaErroneaException extends RuntimeException{

    public FechaErroneaException(){
        super("Error al ingresar la fecha");
    }

}

