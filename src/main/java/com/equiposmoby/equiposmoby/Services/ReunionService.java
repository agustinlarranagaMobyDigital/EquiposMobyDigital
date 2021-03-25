package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReunionService implements IReunionServices {

    @Autowired
    private ReunionDaoImple ReunionDAO;

    @Override
    public List<Reunion> traerTodas() {
        return ReunionDAO.traerTodas();
    }

    @Override
    public void agregar(Reunion reunion) {
        ReunionDAO.agregar(reunion);
    }

    @Override
    public void eliminar(Reunion reunion) {
        ReunionDAO.eliminar(reunion);
    }

    @Override
    public Reunion buscar(String txt) {
        return ReunionDAO.buscar(txt);
    }
}
