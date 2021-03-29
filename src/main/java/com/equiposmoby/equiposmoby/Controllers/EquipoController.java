package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Editors.CuentaPropiertieEditor;
import com.equiposmoby.equiposmoby.Models.Editors.EquipoPropertieEditor;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import com.equiposmoby.equiposmoby.Services.CuentaService;
import com.equiposmoby.equiposmoby.Services.EquipoServiceIMP;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class EquipoController {

    @Autowired
    private EquipoServiceIMP equipoServiceIMP;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaPropiertieEditor cuentaPropiertieEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Cuenta.class,"cuenta",cuentaPropiertieEditor);


    }

    @RequestMapping(value = "/agregarEquipo")
    public String agregarEquipo (Model model){

        model.addAttribute("titulo", "Agregando un equipo");
        model.addAttribute("equipo", new Equipo());
        model.addAttribute("listaCuentas" , cuentaService.traerTodas());

        return "agregar-equipo";
    }

    @PostMapping("/guardarEquipo")
    public String guardarEquipo (Model model, Equipo equipo){
            // el equipo viene con un nombre y una cuenta asignada
            // le asigno una agenda
            equipoServiceIMP.agregarAgenda(equipo);
            // guardo
            equipoServiceIMP.agregar(equipo);

            return "redirect:/listarEquipos";
    }


    @RequestMapping("/listarEquipos")
    public String listarEquipo(Model model){

        List<Equipo> listaEquipos = equipoServiceIMP.traerTodas() ;
        model.addAttribute("titulo" , "Listar Equipo");
        model.addAttribute("listaEquipos" , listaEquipos);

        return "listar-equipos";
    }


    @RequestMapping(value = "/agendaEquipo/{id}")
    public String verAgendaEquipo(Model model, HttpSession session, @PathVariable(value = "id") Integer id){

        return "verAgenda";
    }


    @RequestMapping(value = "/gestionarEquipo/{id}")
    public String gestionarEquipo(Model model, HttpSession session, @PathVariable(value = "id") Integer id){

        Equipo equipo = equipoServiceIMP.getById(id);
        Integrante lider = equipo.getLider();
        List<Integrante> programadores = equipo.getProgramadores();


        model.addAttribute("titulo" , "Gestionar integrantes del equipo: " + equipo.getNombre());
        model.addAttribute("equipo", equipo);
        model.addAttribute("programadores",programadores);
        model.addAttribute("lider", lider);

        return "gestionar-equipo";
    }


    @RequestMapping(value = "/eliminarIntegrante/{id}")
    public String eliminarIntegrante(Model model, HttpSession session, @PathVariable(value = "id") Integer id){

        System.out.println("eliminandoooo id= "+id);

        return "redirect:/gestionar-equipo";
    }

}
