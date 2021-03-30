package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.excepciones.FechaErroneaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;

@Service
public class ReunionService implements IReunionServices {

    @Autowired
    private ReunionDaoImple reunionDAO;

    @Override
    public List<Reunion> traerTodas() {
        return reunionDAO.traerTodas();
    }

    @Override
    public boolean agregar(Reunion reunion) {
        boolean verificarFecha = revisarDiaAnteriorOActual(reunion.getFecha());
        reunionDAO.agregar(reunion);
        return verificarFecha;
    }

    @Override
    public boolean eliminar(Reunion reunion) {
        boolean verificarEliminar = revisarDiaActual(reunion.getFecha());
        reunionDAO.eliminar(reunion);
        return verificarEliminar;
    }

    @Override
    public Reunion buscar(String txt) {
        return reunionDAO.buscar(txt);
    }

    public Reunion agregarReunion(Reunion reunion){


        Reunion reunionNew = Reunion.builder()
                .idReunion(reunion.getIdReunion())
                .fecha(reunion.getFecha())
                .horaInicial(reunion.getHoraInicial())
                .horaFinal(reunion.getHoraFinal())
                .tipoReunion(reunion.getTipoReunion())
                .build();
        agregar(reunionNew);
        return reunionNew;
    }

    public Reunion getById(Integer id) {
        if(id > 0){
            Reunion verificarBuscar = reunionDAO.getById(id);
            return verificarBuscar;
        }
        return null;

    }

    ///----------------------Validaciones------------------------------

    public boolean revisarDiaAnteriorOActual(LocalDate fecha){
        if(fecha != null){
            if(LocalDate.now().isBefore (fecha) || LocalDate.now().isEqual(fecha)){
                return true;
            }
        }
        return false;
    }

    public boolean revisarDiaActual(LocalDate fecha){
        if(fecha != null){
            if(LocalDate.now().isBefore (fecha)){
                return true;
            }
        }
        return false;
    }

    public boolean revisarGetID(Integer id){
        int i=0;
        if(traerTodas().size() != 0){
            while(i < traerTodas().size()){
                if(traerTodas().get(i).getIdReunion().equals(id)){
                    return true;
                }
                else{
                    i++;
                }
            }
        }
        return false;
    }


}
