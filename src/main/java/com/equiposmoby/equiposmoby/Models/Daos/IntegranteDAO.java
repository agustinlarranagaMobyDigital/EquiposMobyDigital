package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import com.equiposmoby.equiposmoby.Models.Entity.Programador;
import org.apache.tomcat.jni.Directory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

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
