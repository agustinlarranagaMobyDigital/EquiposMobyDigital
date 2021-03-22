package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository(value = "lenguajeDAO")
public class LenguajeDAO implements IDao<Object, Integer> {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Object> traerTodas() {
        return em.createQuery("from Lenguaje").getResultList();
    }

    @Override
    public void agregar(Object lenguaje) { em.persist(lenguaje); }

    @Override
    public void eliminar(Object lenguaje) {

    }

    @Override
    public Object buscar(String txt) {
        return null;
    }

    @Override
    public Lenguaje getById(Integer id) {
        return null;
    }

    @Override
    public Lenguaje getById(Integer id) {
        return em.find(Lenguaje.class, id);
    }

}