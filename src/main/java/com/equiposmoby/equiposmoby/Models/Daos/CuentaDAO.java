package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "cuentaDaoJPA")
public class CuentaDAO implements IDao <Cuenta,Integer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cuenta> traerTodas() {
        return em.createQuery("from Cuenta").getResultList();
    }

    @Transactional
    @Override
    public void agregar(Cuenta cuenta) {
        em.persist(cuenta);
    }

    @Override
    public void eliminar(Cuenta cuenta) {

    }

    @Override
    public Cuenta buscar(String txt) {
        return em.find(Cuenta.class,txt);
    }



    @Override
    public Cuenta getById(Integer id) {
        return null;
    }

   /* public Cuenta getByIdEquipo(Integer idEquipo){
        return em.createQuery("from Cuenta c where c.equipo.id = " +  idEquipo).getResultList();
    }*/
}
