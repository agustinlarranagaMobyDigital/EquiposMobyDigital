package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.EquiposmobyApplication;
import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.Date;
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
        return validacion;
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

}