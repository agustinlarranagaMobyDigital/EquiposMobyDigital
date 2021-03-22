package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Equipo;
import com.equiposmoby.equiposmoby.Services.EquipoServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EquipoController {

    @Autowired
    private EquipoServiceIMP equipoServiceIMP;

    @RequestMapping(value = "/formAgregarEquipo")
    public String formAgregarEquipo (Model model){

        Equipo equipo = new Equipo();

        List<Equipo>

        return "formAgregarIntegrante";
    }
}
