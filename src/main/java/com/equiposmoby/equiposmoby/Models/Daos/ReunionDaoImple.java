package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("reunionDaoJPA") ///-> Marco la clase como componente de persistencia de datos
public class ReunionDaoImple implements IDao <Reunion,Integer>{

    @PersistenceContext
    private EntityManager em; //-> se encarga de manejar las clases de entidades

    @SuppressWarnings("unchecked") //-> solucionar errores
    @Override
    public List<Reunion> traerTodas() {
        return em.createQuery("from Reuniones").getResultList();
    }

    @Override
    public void agregar(Reunion reunion) {
        em.persist(reunion);
    }

    @Override
    public void delete(Reunion reunion) {
        em.remove(reunion);
    }
}
