package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.AgendaDAO;
import com.equiposmoby.equiposmoby.Models.Daos.IntegranteDAO;
import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.excepciones.AgendaNoEncontradaException;
import com.equiposmoby.equiposmoby.excepciones.ReunionNoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaDAO agendaDAO;

    @Autowired
    private ReunionDaoImple reunionDaoImple;

    @Autowired
    private IntegranteDAO integranteDAO;

    public Reunion agregar(Integer idAgenda, Reunion reunion) {

        Agenda agenda = agendaDAO.buscarPorId(idAgenda)
                .orElseThrow(AgendaNoEncontradaException::new);
        List<Reunion> reunionList = new ArrayList<>();
        reunionList.add(reunion);
        agenda.setReuniones(reunionList);
        return reunion;
    }

    public Reunion eliminarEvento(Integer idAgenda, Integer idReunion) {

        Agenda agenda = agendaDAO.buscarPorId(idAgenda)
                .orElseThrow(AgendaNoEncontradaException::new);
        Reunion reunion1 = reunionDaoImple.traerReunionPorId(idReunion)
                .orElseThrow(ReunionNoEncontradaException::new);
        if (agenda.getReuniones().contains(reunion1)) {
            agenda.getReuniones().remove(reunion1);
            agendaDAO.modificar(agenda.getIdAgenda(), agenda.getReuniones());
        }
        return reunion1;
    }

    public Agenda eliminar(Integer idAgenda) {

        Agenda agenda = agendaDAO.buscarPorId(idAgenda)
                .orElseThrow(AgendaNoEncontradaException::new);
        agendaDAO.eliminar(agenda);
        return agenda;
    }

    public List<Agenda> traerAgendas() {

        return agendaDAO.traerTodas();
    }

    public Agenda traerPorId(Integer idAgenda) {

        return agendaDAO.getById(idAgenda);
    }

    public Agenda consultaAgendaUsuario(Integer id) {

        Integrante aux = integranteDAO.getById(id);
        return agendaDAO.buscarPorId(aux.getAgenda().getIdAgenda())
                .orElseThrow(AgendaNoEncontradaException::new);
    }
}
