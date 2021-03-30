package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.EquiposmobyApplication;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;

import com.equiposmoby.equiposmoby.Models.Entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import java.util.Map;

@Component
public class ValidacionesService {

    private static Logger LOG = LoggerFactory.getLogger(EquiposmobyApplication.class);


    public boolean validarFecha(Map<String, String> errores,Integrante integrante,LocalDate date) {

        boolean validacion = false;

        validacion = validarEdadIntegrante(errores,integrante);

        if (date != null) {
            if (LocalDate.now().isAfter(date)) {
                validacion = true;
            } else {
                errores.put("edad","Fecha superior o igual a hoy");
            }
        } else {
            errores.put("edad","Fecha vacia");
        }


        return validacion;
    }

    public boolean validarEdadIntegrante(Map<String, String> errores,Integrante integrante) {

        LocalDate minimaLider = LocalDate.now().minusYears(30);
        LocalDate minimaProg = LocalDate.now().minusYears(30);

        boolean validacion=false;

        if ( integrante.getPuesto() != null && integrante.getPuesto().getNombre().equals("lider")) {
            if (integrante.getFechaNacimiento()!=null &&  integrante.getFechaNacimiento().isBefore(minimaLider)) {
                validacion = false;
            }
            else{
                validacion = true;
                errores.put("edad","No admitimos lideres menores de 30 anos");
            }
        }else{
            if (integrante.getFechaNacimiento()!=null && integrante.getFechaNacimiento().isBefore(minimaProg)) {
                validacion = false;
            }
            else{
                errores.put("edad","No admitimos programadores menores de 18 anos");
                validacion = true;
            }
        }
        return true;
    }


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



    public boolean validarIntegrante(Map<String, String> errores,Integrante integrante) {
        boolean validacion = true;
        int i = 0;

        if (integrante.getNombre() == null || integrante.getNombre().isEmpty()) {
            validacion = false;
            errores.put("nombre","Campo nombre vacio");
        }

        if (integrante.getApellido() == null || integrante.getApellido().isEmpty()) {
            validacion = false;
            errores.put("apellido","Campo apellido vacio");
        }

        validacion = validarFecha(errores,integrante, integrante.getFechaNacimiento());

        if (integrante.getExperiencia() == null  || integrante.getExperiencia().toString().isEmpty()){
            validacion = false;
            errores.put("experiencia","Campo experiencia vacio");
        }

        if (integrante.getPuesto() == null || integrante.getPuesto().getNombre().isEmpty()) {
            validacion = false;
            errores.put("puesto","Campo puesto vacio");
        }

        if (integrante.getLenguajes() == null || integrante.getLenguajes().isEmpty()) {
            validacion = false;
            errores.put("lenguajes","Campo lenguajes vacio");
        }
        if (integrante.getEquipo() == null || integrante.getEquipo().getNombre().isEmpty()) {
            validacion = false;
            errores.put("equipo","Campo equipo vacio");
        }

        return validacion;
    }


    public boolean validarNuevoIntegranteAEquipo(Map<String, String> errores, Integrante integrante, List<Integrante> listaIntegrantesEquipo) {
        boolean validacion = true;
        int i = 0;
        do {
            switch (i) {
                case 0 -> {
                    if (integrante.getPuesto().getNombre().equals("lider")) {
                        if(liderCompleto(listaIntegrantesEquipo)){
                            validacion = false;
                            errores.put("nombre","Este equipo ya tiene un lider");
                        }
                    }
                }
                case 1 -> {
                    if (integrante.getPuesto().getNombre().equals("tester")) {
                        if(testerCompleto(listaIntegrantesEquipo)){
                            validacion = false;
                            errores.put("nombre","Este equipo ya tiene 2 testers, no puede haber mas");
                        }
                    }
                }
                case 2 -> {
                    if (integrante.getPuesto().getNombre().equals("backend")) {
                        if(backCompleto(listaIntegrantesEquipo)){
                            validacion = false;
                            errores.put("nombre","Este equipo ya tiene 3 backend, no puede haber mas");
                        }
                    }
                }
                case 3 -> {
                    if (integrante.getPuesto().getNombre().equals("frontend")) {
                        if(frontCompleto(listaIntegrantesEquipo)){
                            validacion = false;
                            errores.put("nombre","Este equipo ya tiene 4 frontend, no puede haber mas");
                        }
                    }
                }
            }
            i++;
        } while (validacion && i < 9);
        return validacion;
    }

    private boolean liderCompleto(List<Integrante> listaIntegrantesEquipo){
        boolean resultado = false;
        for (int i = 0; i < listaIntegrantesEquipo.size(); i++) {
            if (listaIntegrantesEquipo.get(i).getPuesto().getNombre().equals("lider")){
                resultado = true;
                break;
            }
        }
        return  resultado;
    }

    private boolean testerCompleto(List<Integrante> listaIntegrantesEquipo){

        int testers = 0;
        for (int i = 0; i < listaIntegrantesEquipo.size(); i++) {
            if (listaIntegrantesEquipo.get(i).getPuesto().getNombre().equals("tester")){
                testers++;
            }
        }

        if(testers == 2){ return true; }
        else { return  false; }
    }

    private boolean backCompleto(List<Integrante> listaIntegrantesEquipo){

        int backend = 0;
        for (int i = 0; i < listaIntegrantesEquipo.size(); i++) {
            if (listaIntegrantesEquipo.get(i).getPuesto().getNombre().equals("backend")){
                backend++;
            }
        }

        if(backend == 3){ return true; }
        else { return  false; }
    }

    private boolean frontCompleto(List<Integrante> listaIntegrantesEquipo){

        int frontend = 0;
        for (int i = 0; i < listaIntegrantesEquipo.size(); i++) {
            if (listaIntegrantesEquipo.get(i).getPuesto().getNombre().equals("frontend")){
                frontend++;
            }
        }

        if(frontend == 4){ return true; }
        else { return  false; }
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
                    if (reunion.getHoraInicial() != null && reunion.getHoraFinal() != null &&  reunion.getHoraInicial() == reunion.getHoraFinal()) { //No se pueden crear reuniones que comiencen y terminen a la misma hora
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
                    if (reunion.getFecha() == null) { //No se ingresa fecha
                        validacion = false;
                        errores.put("fechaNull","Debe ingresar una fecha");
                    }
                }
                case 4 -> {
                    if (revisarFechaPasada(reunion.getFecha()) == false) { //No se puede ingresar una fecha que sea anterior a la actualidad
                        validacion = false;
                        errores.put("fechaAnterior","No se puede ingresar una fecha anterior a la actual");
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