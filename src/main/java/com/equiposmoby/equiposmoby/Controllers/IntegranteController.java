package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Programador;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntegranteController {

    private String titulo = "Equipos Moby Digital!";
    @Autowired
    private IntegranteService integranteService;


    @RequestMapping(value = "/formIntegrante")
    public String formIntegrante (Model model){

        Integrante integrante = new Integrante();

        model.addAttribute("titulo",titulo);
        model.addAttribute("h1","Formulario del nuevo empleado de Moby Digital!");
        model.addAttribute("integrante",integrante);

        return "formIntegrante";
    }


    @PostMapping(value = "/addIntegrante")
    public String addIntegrante(Model model,  Integrante integrante ){

        Programador programador = integrante;
        integranteService

        return "redirect:listarClientes";
    }

}
