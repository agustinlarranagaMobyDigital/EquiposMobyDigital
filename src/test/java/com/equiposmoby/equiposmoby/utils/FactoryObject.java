package com.equiposmoby.equiposmoby.utils;

import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Models.Entity.TipoReunion;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface FactoryObject {

    default String createJsonRequest(Object object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(object);
    }


    default Cuenta createCuenta() {
        return Cuenta.builder()
                .id_cuenta(1)
                .nombre("cuentatest")
                .valor(123.0)
                .build();
    }

    default User createUsuario() {
        return User.builder().email("taka@gamil.com").password("123").build();
    }

    default Puesto createPuesto() {
        return Puesto.builder().
                id(1).
                nombre("porgramador").
                build();
    }

    default Lenguaje createLenguaje() {
        return Lenguaje.builder()
                .nombre("Quechua")
                .id(5)
                .build();

    }

    default Integrante createIntegrante() {
        return Integrante.builder()
                .id(1)
                .nombre("name")
                .apellido("surname")
                .experiencia(1)
                .agenda(createAgenda())
                .fechaNacimiento(LocalDate.now())
                .lider(false)
                .usuario(createUsuario())
                .puesto(createPuesto())
                .lenguajes(cargarListDeLenguaje()).build();
    }



    default Agenda createAgenda() {

        return Agenda.builder()
                .idAgenda(1)
                .reuniones(new ArrayList<Reunion>())
                .build();
    }


    default Agenda createAgenda2() {

        return Agenda.builder()
                .idAgenda(2)
                .reuniones(new ArrayList<Reunion>())
                .build();
    }


    default Equipo createEquipo() {

        return Equipo.builder()
                .id(1)
                .nombre("Dream Team")
                .cuenta(createCuenta())
                .agenda(createAgenda())
                .arrayList(cargarListDeIntegrante())
                .build();
    }

    default Equipo createEquipo2() {

        return Equipo.builder()
                .id(2)
                .nombre("Dream Team")
                .cuenta(createCuenta())
                .agenda(createAgenda())
                .arrayList(cargarListDeIntegrante())
                .build();
    }

    default Reunion createReunion() {

        return Reunion.builder()
                .idReunion(1)
                .fecha(LocalDate.of(121, 8,22))
                .horaInicial(LocalTime.of(10, 5, 6))
                .horaFinal(LocalTime.of(11, 0, 6))
                .tipoReunion(new TipoReunion())
                .build();
    }


    default Reunion createReunion2() {

        return Reunion.builder()
                .idReunion(2)
                .fecha(LocalDate.of(121, 8,22))
                .horaInicial(LocalTime.of(10, 5, 6))
                .horaFinal(LocalTime.of(11, 0, 6))
                .tipoReunion(new TipoReunion())
                .build();
    }

    default List<Lenguaje> cargarListDeLenguaje() {

        List<Lenguaje> lenguajes = new ArrayList<>();
        lenguajes.add(createLenguaje());
        return lenguajes;
    }

    default List<Integrante> cargarListDeIntegrante() {

        List<Integrante> integrantes = new ArrayList<>();
        integrantes.add(createIntegrante());
        return integrantes;
    }

    default List<Reunion> cargarListDeReunion() {

        List<Reunion> reunions = new ArrayList<>();
        reunions.add(createReunion());
        return reunions;
    }

    default List<Agenda> cargarListDeAgenda() {

        List<Agenda> agendaList = new ArrayList<>();
        agendaList.add(createAgenda());
        //agendaList.add(createAgenda2());
        return agendaList;
    }

    default Cuenta createCuenta2(){

        return Cuenta.builder()
                .id_cuenta(2)
                .nombre("ICBC")
                .valor(170.0)
                .build();
    }

    default List<Cuenta> cargarListCuenta(){

        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.add(createCuenta());
        return cuentas;
    }
    default List<Equipo> cargarListDeEquipo(){
        List<Equipo> equipoList = new ArrayList<>();
        equipoList.add(createEquipo());
        return equipoList;
    }


}
