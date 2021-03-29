package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;


@Repository(value = "equipoDAO")
public class EquipoDAO implements IDao<Equipo, Integer>{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Equipo> traerTodas() { return em.createQuery("from Equipo").getResultList(); }

    @Transactional
    @Override
    public void agregar(Equipo equipo) {
        em.persist(equipo);
    }

    @Override
    public void eliminar(Equipo equipo){
        em.remove(equipo);
    }

    @Override
    public Equipo buscar(String txt) {
        return em.find(Equipo.class , txt);
    }

    @Override
    public Equipo getById(Integer id) {
        return em.find(Equipo.class, id);
    }

}
