package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.IDao;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import com.equiposmoby.equiposmoby.Models.Entity.Puesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegranteService {

    @Autowired
    @Qualifier("integranteDAO")
    private IDao integranteDAO;



    public void add(Integrante integrante){
        integranteDAO.agregar(integrante);
    }

    @Autowired
    @Qualifier("lenguajeDAO")
    private IDao lenguajeDAO;

    public List<Lenguaje> getLenguajes() {
        return lenguajeDAO.traerTodas();
    }


    @Autowired
    @Qualifier("puestoDAO")
    private IDao puestoDAO;

    public List<Puesto> getPuestos() {
        return puestoDAO.traerTodas();
    }

}
