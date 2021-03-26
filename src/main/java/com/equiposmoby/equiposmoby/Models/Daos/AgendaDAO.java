package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.excepciones.AgendaNoEncontradaException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository("agendaDao")
public class AgendaDAO implements IDao<Agenda, Integer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Agenda> traerTodas() {
        return em.createQuery("from Agenda").getResultList();
    }

    @Transactional
    @Override
    public void agregar(Agenda agenda) {
        em.persist(agenda);
    }

    @Transactional
    @Override
    public void eliminar(Agenda agenda) {
        em.remove(agenda);
    }

    @Override
    public Agenda buscar(String txt) {
        return null;
    }

    @Transactional
    @Override
    public Agenda getById(Integer id) {

        return buscarPorId(id)
                .orElseThrow(AgendaNoEncontradaException::new);
    }

    public Agenda modificar(Integer id, List<Reunion> reunions) {

        Agenda agenda = buscarPorId(id)
                .orElseThrow(AgendaNoEncontradaException::new);;
        return ModificarAgenda(agenda, reunions);
    }

    public Optional<Agenda> buscarPorId(Integer id) {

        return Optional.ofNullable(em.find(Agenda.class, id));
    }

    @Transactional
    public Agenda ModificarAgenda(Agenda agenda,List<Reunion> reunions){

        agenda.setReuniones(reunions);
        em.refresh(agenda);
        return agenda;
    }
}
