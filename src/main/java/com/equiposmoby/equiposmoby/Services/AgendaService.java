package com.equiposmoby.equiposmoby.Services;

import com.equiposmoby.equiposmoby.Models.Daos.AgendaDAO;
import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AgendaService {

    private final AgendaDAO agendaDAO;

    public Boolean agregar(Integer idAgenda, Reunion reunion){

        Agenda agenda = agendaDAO.buscarPorId(idAgenda);
        List<Reunion> reunionList = new ArrayList<>();
        reunionList.add(reunion);
        agenda.setReuniones(reunionList);
        return true;
    }

}
