package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.TipoReunion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "tipoReunionDao")
public class TipoReunionDAO implements IDao<TipoReunion, Integer> {


    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<TipoReunion> traerTodas() {
        return em.createQuery("from TipoReunion").getResultList();
    }

    @Transactional
    @Override
    public void agregar(TipoReunion tipoReunion) {
        em.persist(tipoReunion);
    }

    @Override
    public void eliminar(TipoReunion tipoReunion) {
        em.remove(tipoReunion);
    }

    @Override
    public TipoReunion buscar(String txt) {
        return null;
    }

    @Override
    public TipoReunion getById(Integer id) {
        return em.find(TipoReunion.class, id);
    }
}
