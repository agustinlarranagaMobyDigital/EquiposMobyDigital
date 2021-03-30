package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.EquiposmobyApplication;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.Map;

public class ValidacionesService {

    private static Logger LOG = LoggerFactory.getLogger(EquiposmobyApplication.class);


    ///----------------------Validaciones Reuniones --------------------------

    public boolean revisarFechaPasada(LocalDate fecha){
        if(fecha != null){
            if(LocalDate.now().isAfter (fecha)){
                return false;
            }
        }
        return true;
    }

    public boolean revisarDiaActual(LocalDate fecha){
        if(fecha != null){
            if(LocalDate.now().isBefore (fecha)){
                return false;
            }
        }
        return true;
    }

    /** Generica */
    ///Si devuelve true esta bien
    public boolean revisarReunion(Reunion reunion, Map<String,String> errores){
        boolean validacion = true;
        int i=0;
        LocalTime horaInicio= LocalTime.of(8,00);
        LocalTime horaFinal= LocalTime.of(18,00);

        do {
            switch (i) {
                case 0 -> {
                    if (reunion.getHoraInicial() == reunion.getHoraFinal()) { //No se pueden crear reuniones que comiencen y terminen a la misma hora
                        validacion = false;
                        errores.put("coincidencia","No se puede crear una reunion que inicie y finalice a la misma hora");
                    }
                }
                case 1 -> {
                    if (reunion.getHoraInicial() == null) { //No se ingresa hora inicial
                        validacion = false;
                        errores.put("horaInicial","Debe ingresar una hora inicial");
                    }
                }
                case 2 -> {
                    if (reunion.getHoraFinal() == null) { //No se ingresa hora final
                        validacion = false;
                        errores.put("horaFinal","Debe ingresar una hora final");
                    }
                }
                case 3 -> {
                    if (revisarFechaPasada(reunion.getFecha()) == false) { //No se puede ingresar una fecha que sea anterior a la actualidad
                        validacion = false;
                        errores.put("fechaAnterior","No se puede ingresar una fecha anterior a la actual");
                    }
                }
                case 4 -> {
                    if (reunion.getFecha() == null) { //No se ingresa fecha
                        validacion = false;
                        errores.put("fechaNull","Debe ingresar una fecha");
                    }
                }
                case 5 -> {
                    if (reunion.getHoraInicial().isBefore((horaInicio))) { //No se puede crear antes de las 8:00
                        validacion = false;
                        errores.put("horarioAnterior","No puede crear una reunion antes de las 8:00");
                    }
                }
                case 6 -> {
                    if (reunion.getHoraFinal().isAfter(horaFinal)) { //No se puede crear despues de las 17:00
                        validacion = false;
                        errores.put("horarioPosterior","No puede crear una reunion despues de las 18:00");
                    }
                }
            }
            i++;
        }while(validacion && i<6);
        return validacion;
    }



}
