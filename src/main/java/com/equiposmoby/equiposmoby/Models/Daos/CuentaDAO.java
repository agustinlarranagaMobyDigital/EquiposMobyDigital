package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "cuentaDaoJPA")
public class CuentaDAO implements IDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cuenta> traerTodas() {
        return em.createQuery("from Cuenta").getResultList();
    }

    @Override
    public void agregar(Object o) {

    }

    @Override
    public void eliminar(Object o) {

    }

    @Override
    public Cuenta buscar(String txt) {
        return em.find(Cuenta.class,txt);
    }



    @Override
    public Cuenta getById(Integer id) {
        return null;
    }
}
