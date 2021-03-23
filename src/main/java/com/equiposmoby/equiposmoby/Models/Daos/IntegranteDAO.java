package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "integranteDAO")
public class IntegranteDAO implements IDao<Integrante, Integer> {

    @PersistenceContext
    private EntityManager em;


    @Transactional(readOnly = true)
    @Override
    public List<Integrante> traerTodas() {
        return em.createQuery("from Integrante").getResultList();
    }

    @Transactional
    @Override
    public void agregar(Integrante integrante) {
        em.persist(integrante);
    }

    @Transactional
    @Override
    public void eliminar(Integrante integrante) {
        em.remove(integrante);
    }

    @Transactional(readOnly = true)
    @Override
    public Integrante buscar(String txt) {
        return em.find(Integrante.class , txt);
    }

    @Transactional(readOnly = true)
    @Override
    public Integrante getById(Integer id) {
        return em.find(Integrante.class, id);
    }


}
