package com.equiposmoby.equiposmoby.utils;

import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Models.Entity.TipoReunion;
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

    default Agenda createAgenda (){

        return Agenda.builder()
                .idAgenda(1)
                .reuniones(new ArrayList<Reunion>())
                .build();
    }

    default Agenda createAgenda2 (){

        return Agenda.builder()
                .idAgenda(2)
                .reuniones(new ArrayList<Reunion>())
                .build();
    }


    default Reunion createReunion(){

        return Reunion.builder()
                .idReunion(1)
                .fecha(LocalDate.of(121, 8,22))
                .horaInicial(LocalTime.of(10, 5, 6))
                .horaFinal(LocalTime.of(11, 0, 6))
                .tipoReunion(new TipoReunion())
                .build();
    }

    default Reunion createReunion2(){

        return Reunion.builder()
                .idReunion(2)
                .fecha(LocalDate.of(121, 8,22))
                .horaInicial(LocalTime.of(10, 5, 6))
                .horaFinal(LocalTime.of(11, 0, 6))
                .tipoReunion(new TipoReunion())
                .build();
    }

    default List<Reunion> cargarListDeReunion(){

        List<Reunion> reunions = new ArrayList<>();
        reunions.add(createReunion());
        return reunions;
    }

    default List<Agenda> cargarListDeAgenda(){

        List<Agenda> agendaList = new ArrayList<>();
        agendaList.add(createAgenda());
        //agendaList.add(createAgenda2());
        return agendaList;
    }

}
