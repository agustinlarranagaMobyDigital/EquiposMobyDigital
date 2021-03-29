package com.equiposmoby.equiposmoby.excepciones;

public class IdIncorrectoException extends RuntimeException{

    public IdIncorrectoException(){
        super("El ID no ha sido encontrado");
    }

}
