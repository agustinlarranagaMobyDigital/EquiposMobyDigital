package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.AgendaDAO;
import com.equiposmoby.equiposmoby.Models.Daos.IntegranteDAO;
import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.excepciones.AgendaNoCreadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaDAO agendaDAO;

    @Autowired
    private IntegranteDAO integranteDAO;

    public Boolean agregar(Integer idAgenda, Reunion reunion) {

        Agenda agenda = agendaDAO.buscarPorId(idAgenda);
        List<Reunion> reunionList = new ArrayList<>();
        try {
            reunionList.add(reunion);
            agenda.setReuniones(reunionList);
            return true;
        }catch (AgendaNoCreadaException ex){
            throw new AgendaNoCreadaException("No se pudo crear la agenda");
        }
    }

    public Boolean agregarAgendaIntegrante(Integrante integrante){

        Agenda agenda = Agenda.builder()
                .reuniones(new ArrayList<Reunion>())
                .build();
        integrante.setAgenda(agenda);
        integranteDAO.modificar(integrante);
        return true;
    }

    public void modificar(Integrante integrante, Agenda a){

        integrante.setAgenda(a);
        integranteDAO.agregar(integrante);
    }

    public Reunion eliminarEvento(Integer idAgenda, Integer idReunion) {

        Agenda agenda = agendaDAO.buscarPorId(idAgenda);
        List<Reunion> reuniones = agenda.getReuniones();
        for (Reunion reunion : reuniones) {
            if (reunion.getIdReunion().equals(idReunion)) {
                reuniones.remove(reunion);
                agendaDAO.modificar(agenda.getIdAgenda(), reuniones);
                return reunion;
            }
        }
        return null;
    }

    public Agenda eliminar(Integer idAgenda) {

        Agenda agenda = agendaDAO.buscarPorId(idAgenda);
        agendaDAO.eliminar(agenda);
        return agenda;
    }

    public List<Agenda> traerAgendas(){

        return agendaDAO.traerTodas();
    }

    public Agenda traerPorId(Integer idAgenda){

        return agendaDAO.getById(idAgenda);
    }

}
