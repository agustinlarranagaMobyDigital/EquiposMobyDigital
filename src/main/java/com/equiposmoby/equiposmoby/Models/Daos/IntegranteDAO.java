package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import com.equiposmoby.equiposmoby.Models.Entity.Programador;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "integranteDAO")
public class IntegranteDAO implements IDao<Integrante, Integer> {

    @PersistenceContext
    private EntityManager em;



    @Override
    public List traerTodas() {
        return null;
    }

    @Transactional
    @Override
    public void agregar(Integrante integrante) {
        em.persist(integrante);
    }

    @Override
    public void eliminar(Integrante integrante) {
        em.remove(integrante);
    }

    @Override
    public Integrante buscar(String txt) {
        return em.find(Integrante.class , txt);
    }




}
