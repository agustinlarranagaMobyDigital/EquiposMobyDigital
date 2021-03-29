package com.equiposmoby.equiposmoby.excepciones;

public class ReunionNoEncontrada extends RuntimeException{

    public ReunionNoEncontrada(){
        super("La reunion no ha sido encontrada");
    }
}
