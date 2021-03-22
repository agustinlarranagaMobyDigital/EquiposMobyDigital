package com.equiposmoby.equiposmoby.Models.Daos;

import com.equiposmoby.equiposmoby.Models.Entity.Agenda;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class AgendaDAO implements IDao<Agenda,Integer>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Agenda> traerTodas() {
        return null;
    }

    @Override
    public void agregar(Agenda agenda) {

    }

    @Override
    public void eliminar(Agenda agenda) {

    }

    @Override
    public Agenda buscar(String txt) {
        return null;
    }

    public Agenda buscarPorId(Integer id) {

        if(em.createQuery("Select from agendas where id = " + id) != null){
            return (Agenda)em.createQuery("Select from agendas where id = " + id);
        }else {
             throw new NullPointerException();
        }
    }
}
