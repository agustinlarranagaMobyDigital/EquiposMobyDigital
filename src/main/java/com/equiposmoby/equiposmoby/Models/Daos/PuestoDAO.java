package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import com.equiposmoby.equiposmoby.Models.Entity.Puesto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository(value = "puestoDAO")
public class PuestoDAO implements IDao<Puesto, Integer> {

    private EntityManager em;
    @Override
    @Transactional(readOnly = true)
    public List<Puesto> traerTodas() {
        return em.createQuery("from Puesto").getResultList();
    }

    @Override
    public void agregar(Puesto puesto) {

    }

    @Override
    public void eliminar(Puesto puesto) {

    }

    @Override
    public Puesto buscar(String txt) {
        return null;
    }

}