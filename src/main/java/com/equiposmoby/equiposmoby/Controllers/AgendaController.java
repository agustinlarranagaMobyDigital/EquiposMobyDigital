package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Services.AgendaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;


    @PostMapping("/{id-agenda}")
    public ResponseEntity agregarEvento(@PathVariable(value = "id-agenda") Integer idAgenda, @RequestBody Reunion reunion) {

        return ResponseEntity.ok(agendaService.agregar(idAgenda, reunion));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Agenda>> traerTodas() {

        return ResponseEntity.ok(agendaService.traerAgendas());
    }

    @GetMapping("/{id-agenda}")
    public ResponseEntity<Agenda> traerPorId(@PathVariable(value = "id-agenda") Integer idAgenda) {

        return ResponseEntity.ok(agendaService.traerPorId(idAgenda));
    }

    @DeleteMapping("/{id-agenda}" + "/{id-reunion}")
    public ResponseEntity<Reunion> eliminarReunion(@PathVariable(value = "id-agenda") Integer idAgenda,
                                                   @PathVariable(value = "id-reunion") Integer idReunion) {

        return ResponseEntity.ok(agendaService.eliminarEvento(idAgenda, idReunion));
    }

    @DeleteMapping("/{id-agenda}")
    public ResponseEntity<Agenda> eliminarAgenda(@PathVariable(value = "id-agenda") Integer idAgenda) {

        return ResponseEntity.ok(agendaService.eliminar(idAgenda));
    }

    @GetMapping("/consulta/{id-integrante}")
    public String consultarAgendaPersonal(
            @PathVariable(value = "id-integrante") Integer idIntegrante, Model model){

        Agenda agenda = agendaService.consultaAgendaUsuario(idIntegrante);

        System.out.println("agenda.toString = " + agenda.toString());
        model.addAttribute("agenda",agenda);
        return "agendaPersonal";
    }

    @GetMapping("/consultaEquipo/{id-equipo}")
    public String consultarAgendaEquipo(
            @PathVariable(value = "id-equipo") Integer idEquipo, Model model){

        Agenda agenda = agendaService.consultaAgendaEquipo(idEquipo);
        System.out.println("agenda.toString = " + agenda.toString());
        model.addAttribute("agenda",agenda);
        return "agendaEquipo";
    }
}
