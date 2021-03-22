package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Agenda;
import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;
import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Services.EquipoServiceIMP;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class EquipoController {

    @Autowired
    private EquipoServiceIMP equipoServiceIMP;

    @Autowired
    private IntegranteService integranteService;

    @RequestMapping(value = "/agregarEquipo")
    public String agregarEquipo (Model model){
        model.addAttribute("titulo", "Agregando un equipo");
        model.addAttribute("equipo", new Equipo());
        model.addAttribute("listaLideres" , integranteService.obtenerLideres());

        return "agregar-equipo";
    }

    @PostMapping("/agregarEquipo")
    public String guardarEquipo (Model model, @RequestParam String nombre , @RequestParam Integer lider){

            ArrayList<Integrante> lideres = integranteService.obtenerLideres();
            model.addAttribute("titulo" , "Agregando un equipo");
            model.addAttribute("equipo" , new Equipo());
            model.addAttribute("listalideres" , lideres);
            ArrayList<Integrante> integrantes = new ArrayList<Integrante>();
            integrantes.add(integranteService.getById(lider));
            Equipo equipo = Equipo.builder().arrayList(integrantes).cuenta(null).nombre(nombre).agenda(null).build();
            equipoServiceIMP.agregar(equipo);

            return "listar-equipos";
    }


    @RequestMapping("/listarEquipos")
    public String listarEquipo(Model model){
        model.addAttribute("titulo" , "Listar Equipo");
        model.addAttribute("Equipos" , equipoServiceIMP.traerTodas());

        return "listar-equipos";
    }
}
