package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Services.EquipoServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EquipoController {

    @Autowired
    private EquipoServiceIMP equipoServiceIMP;

    @RequestMapping(value = "/agregarEquipo")
    public String agregarEquipo (Model model){
        model.addAttribute("titulo", "Agregando un equipo");
        model.addAttribute("equipos", equipoServiceIMP.traerTodas());

        return "agregarEquipo";
    }
}
