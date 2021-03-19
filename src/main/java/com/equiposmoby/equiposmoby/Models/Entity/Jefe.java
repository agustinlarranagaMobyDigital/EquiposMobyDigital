package com.equiposmoby.equiposmoby.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
@AllArgsConstructor
@NoArgsConstructor*/
public class Jefe extends Integrante{

    public Jefe () throws ParseException {
        this.setNombre("Horacio");
        this.setApellido("Castañeda");
        this.setFechaNacimiento(new Date(1980, 02 ,29));
        this.setExperiencia(3);
        this.setJefe(true);
        this.setPuesto(new Puesto());
        this.setAgenda(new Agenda());
        this.setLenguajes(new ArrayList<Lenguaje>());
    }
    public String mensaje(){return "";}


}
