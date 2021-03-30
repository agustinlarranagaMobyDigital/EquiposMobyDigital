package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;

import com.equiposmoby.equiposmoby.Models.Entity.TipoReunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoReunionService {

    @Autowired
    @Qualifier("tipoReunionDao")
    private IDao tipoReunionDAO;

    public List<TipoReunion> getAll(){
        return tipoReunionDAO.traerTodas();
    }

    public TipoReunion getById(Integer id){
        return (TipoReunion) tipoReunionDAO.getById(id);
    }
}
