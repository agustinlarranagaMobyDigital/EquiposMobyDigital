package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.TipoReunionDAO;
import com.equiposmoby.equiposmoby.Models.Entity.TipoReunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoReunionService {

    @Autowired
    private TipoReunionDAO tipoReunionDAO;

    public List<TipoReunion> getAll(){

        return tipoReunionDAO.traerTodas();
    }
}
