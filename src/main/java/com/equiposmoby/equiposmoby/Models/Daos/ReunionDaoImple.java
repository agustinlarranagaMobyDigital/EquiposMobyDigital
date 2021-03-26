package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository(value = "reunionDaoJPA") ///-> Marco la clase como componente de persistencia de datos
public class ReunionDaoImple implements IDao <Reunion,Integer>{

    @PersistenceContext
    private EntityManager em; //-> se encarga de manejar las clases de entidades

    @Transactional
    @Override
    public List<Reunion> traerTodas() {
        return em.createQuery("from Reunion").getResultList();
    }

    @Transactional
    @Override
    public void agregar(Reunion reunion) {
        em.persist(reunion);
    }

    @Transactional
    @Override
    public void eliminar(Reunion reunion) {
        em.remove(reunion);
    }

    @Transactional
    @Override
    public Reunion buscar(String txt) {
        return em.find(Reunion.class , txt);
    }

    @Transactional
    @Override
    public Reunion getById(Integer id) {
        return em.find(Reunion.class , id);
    }

    public Optional<Reunion> traerReunionPorId(Integer id){

        return Optional.ofNullable(getById(id));
    }

}
