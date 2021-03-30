package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReunionService extends ValidacionesService implements IReunionServices {

    @Autowired
    private ReunionDaoImple reunionDAO;

    @Override
    public List<Reunion> traerTodas() {
        return reunionDAO.traerTodas();
    }

    @Override
    public boolean agregar(Reunion reunion) {
        Map<String , String> errores = new HashMap<>();
        boolean verificarFecha = revisarReunion(reunion,errores);
        reunionDAO.agregar(reunion);
        return verificarFecha;
    }

    @Override
    public boolean eliminar(Reunion reunion) {
        boolean verificarEliminar = revisarDiaActual(reunion.getFecha());
        if(verificarEliminar == true){
            reunionDAO.eliminar(reunion);
        }
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


}
