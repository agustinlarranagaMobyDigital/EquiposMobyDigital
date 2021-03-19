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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IntegranteController {

    private String titulo = "Equipos Moby Digital!";

    @Autowired
    private IntegranteService integranteService;


    @RequestMapping(value = "/formIntegrante")
    public String formIntegrante (Model model){

        Integrante integrante = new Integrante();

        List<Puesto> listaPuestos = integranteService.getPuestos();
        List<Lenguaje> listaLenguajes = integranteService.getLenguajes();

        model.addAttribute("titulo",titulo);
        model.addAttribute("h1","Formulario del nuevo empleado de Moby Digital!");
        model.addAttribute("integrante",integrante);


        return "formIntegrante";
    }


    @PostMapping(value = "/addIntegrante")
    public String addIntegrante(Model model, Integrante integrante){


        List<Lenguaje> listaLenguajes = integranteService.getLenguajes();
        integrante.setLenguajes(listaLenguajes);

        List<Integrante> listaparaLenguajes = new ArrayList<>();
        listaparaLenguajes.add(integrante);
        for (int i = 0; i < listaLenguajes.size(); i++) {
            listaLenguajes.get(i).setIntegrantes(listaparaLenguajes);
        }

        System.out.println("listaLenguajes = " +  integrante.getLenguajes().toString());

        List<Puesto> listaPuestos = integranteService.getPuestos();
        integrante.setPuesto(listaPuestos.get(1));


      //  integranteService.add(integrante);

        return "redirect:formIntegrante";
    }

}
