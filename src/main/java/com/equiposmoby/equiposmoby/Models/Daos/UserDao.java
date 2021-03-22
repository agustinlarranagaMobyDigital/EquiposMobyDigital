package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("usuarioDAO")
public class UserDao implements IDao<User , Integer>{

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<User> traerTodas() {
       return em.createQuery("from User").getResultList();
    }

    @Transactional
    @Override
    public void agregar(User user) {
        em.persist(user);
    }

    @Transactional
    @Override
    public void eliminar(User user) {
        em.remove(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User buscar(String email){

        return em.find(User.class , email);
    }

    @Override
    public User getById(Integer id) {
        return null;
    }
}
