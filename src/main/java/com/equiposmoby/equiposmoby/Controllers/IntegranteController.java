package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import com.equiposmoby.equiposmoby.Models.Entity.Puesto;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IntegranteController {

    private String titulo = "Equipos Moby Digital!";

    @Autowired
    private IntegranteService integranteService;


    @RequestMapping(value = "/formIntegrante")
    public String formIntegrante (Model model){

        Integrante integrante = new Integrante();

        List<Lenguaje> lenguajes = integranteService.getLenguajes();
        List<Puesto> puestos = integranteService.getPuestos();

        model.addAttribute("titulo",titulo);
        model.addAttribute("h1","Formulario del nuevo empleado de Moby Digital!");
        model.addAttribute("integrante",integrante);
        model.addAttribute("lenguajes",lenguajes);
        model.addAttribute("puestos",puestos);

        return "formIntegrante";
    }


    @PostMapping(value = "/addIntegrante")
    public String addIntegrante(Model model,  Integrante integrante ){

        integranteService.add(integrante);

        return "redirect:formIntegrante";
    }

}
