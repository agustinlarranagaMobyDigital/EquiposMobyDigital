package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository(value = "lenguajeDAO")
public class LenguajeDAO implements IDao<Lenguaje, Integer> {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Lenguaje> traerTodas() {
        return em.createQuery("from Lenguaje").getResultList();
    }

    @Override
    public void agregar(Lenguaje lenguaje) { em.persist(lenguaje); }

    @Override
    public void eliminar(Lenguaje lenguaje) {

    }

    @Override
    public Lenguaje buscar(String txt) {
        return null;
    }

    @Override
    public Lenguaje getById(Integer id) {
        return null;
    }

}