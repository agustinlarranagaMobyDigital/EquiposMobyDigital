package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.EquiposmobyApplication;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Date;

public class ValidacionesService {

    private static Logger LOG = LoggerFactory.getLogger(EquiposmobyApplication.class);

    public boolean validarFecha(LocalDate date){
        boolean validacion = false;
        if(date != null){
            if(LocalDate.now().isAfter(date)){
                validacion = true;
            }else{
                LOG.error("Fecha superior o igual a hoy");
            }
        }else{
            LOG.error("Fecha de integrante vacio");
        }
        return validacion;
    }

    public boolean validarIntegrante(Integrante integrante){
        boolean validacion = true;
        int i = 0;
       do{
               switch (i){
                   case 0->{ if (integrante.getNombre() == null ){
                       validacion = false;
                       LOG.error("Nombre de integrante vacio");
                   }}
                   case 1->{ if(integrante.getApellido() == null){
                       validacion = false;
                       LOG.error("Apellido de integrante vacio");
                   }}
                   case 2->{ validacion = validarFecha(integrante.getFechaNacimiento()); }

                   case 3->{ if( integrante.getExperiencia() <= 0){
                       validacion = false;
                       LOG.error("Edad de integrante nula o menor a 0");
                   }
                   }
                   case 4->{ if(integrante.getAgenda() == null){
                            validacion = false;
                           LOG.error("Agenda de integrante vacio");

                   }
                   }
                   case 5->{ if(integrante.getPuesto() == null){
                            validacion = false;
                            LOG.error("Puesto del integrante vacio");
                   }
                   }
                   case 6->{ if(integrante.getLenguajes() == null){
                            validacion = false;
                           LOG.error("Lenguajes de integrante vacio");

                   }
                   }
                   case 7->{ if (integrante.getUsuario() == null){
                            validacion= false;
                            LOG.error("Usuario del integrante vacio");
                   }
                   }
               }
           i++;
        }while (validacion &&  i < 9);
       return validacion;
    }

}
