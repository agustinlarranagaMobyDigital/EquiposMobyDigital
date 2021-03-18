package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Programador;
import com.equiposmoby.equiposmoby.Models.Entity.Puesto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IntegranteDAO implements IntegranteRepository {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    @Override
    public void addProgramador(Programador programador) {
        em.persist(programador);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Programador> getAllProgramadores(){

        Puesto puesto = (Puesto) em.createQuery("from Puesto where nombre='lider'").getResultList();

        ArrayList<Programador> lista = (ArrayList<Programador>) em.createQuery("from Integrante where id_puesto=" + puesto.getId()).getResultList();

        return lista;
    }

}
