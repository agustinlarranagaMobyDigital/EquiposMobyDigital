package com.equiposmoby.equiposmoby.Models.Daos;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;

@Repository(value = "equipoDAO")
public class EquipoDAO implements IDao<Object, Integer> {

    @PersistenceContext
    private EntityManager em;

    @Override //Consultar
    public List<Object> traerTodas(){ return em.createQuery("from Equipo").getResultList(); }

    @Override
    public void agregar(Object equipo) {em.persist(equipo); }

    @Override
    public void eliminar(Object equipo){

    }

    @Override
    public Object buscar(String txt) { return null; }

}
