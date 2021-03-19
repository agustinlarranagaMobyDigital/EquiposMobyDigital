package com.equiposmoby.equiposmoby.Models.Daos;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "reunionDaoJPA") ///-> Marco la clase como componente de persistencia de datos
public class ReunionDaoImple implements IDao <Reunion,Integer>{

    @PersistenceContext
    private EntityManager em; //-> se encarga de manejar las clases de entidades

    @Override
    public List<Reunion> traerTodas() {
        return em.createQuery("from Reunion").getResultList();
    }

    @Override
    public void agregar(Reunion reunion) {
        em.persist(reunion);
    }

    @Override
    public void eliminar(Reunion reunion) {
        em.remove(reunion);
    }

    @Override
    public Reunion buscar(String txt) {
        return em.find(Reunion.class , txt);
    }


}
