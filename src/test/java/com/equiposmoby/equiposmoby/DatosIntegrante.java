package com.equiposmoby.equiposmoby;

import com.equiposmoby.equiposmoby.Models.Entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatosIntegrante {

    public final static List<Lenguaje> LENGUAJES = Arrays.asList(
                                                                new Lenguaje(1,"java"),
                                                                new Lenguaje(2,"php"),
                                                                new Lenguaje(3,"css"),
                                                                new Lenguaje(4,"phyton"));


    public final static Integrante INTEGRANTE = new Integrante(1,"agustin","larra", LocalDate.now(),1,false,
                                                                    new Equipo(),new Puesto(),
                                                                    Arrays.asList( new Lenguaje(1,"java"), new Lenguaje(2,"php")),
                                                                    new Agenda(),new User());

    public final static List<Puesto> PUESTOS = Arrays.asList(
            new Puesto(1,"tester"),
            new Puesto(2,"front"),
            new Puesto(3,"back"),
            new Puesto(4,"lider"));


    public final static List<Equipo> EQUIPOS = Arrays.asList(
            new Equipo(1,"trainies"),
            new Equipo(2,"rocket"),
            new Equipo(3,"proyecto interno"),
            new Equipo(4,"icbc"));
}
