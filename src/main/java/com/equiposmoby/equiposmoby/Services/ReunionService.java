package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void agregar(Reunion reunion) {

        reunionDAO.agregar(reunion);
    }

    @Override
    public void eliminar(Reunion reunion) {
        reunionDAO.eliminar(reunion);
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
}
