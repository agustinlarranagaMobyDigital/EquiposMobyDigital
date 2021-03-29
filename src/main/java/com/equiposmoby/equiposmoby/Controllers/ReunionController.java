package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Services.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
public class ReunionController {

    private String titulo = "Reuniones MobyDigital";

    @Autowired
    private ReunionService reunionService;


    @RequestMapping(value = "/agregarReunion")
    public String addReunion(Model model){
        model.addAttribute("titulo","Agregar Reunion");
        model.addAttribute("agregar",reunionService.agregar(new Reunion()));
        return "agregar-reunion";
    }

    @RequestMapping( "/listarReuniones") // Si esta vacio, implicitamente es GET
    public String listarReuniones(Model model, Integer id){
        model.addAttribute("listar", reunionService.traerTodas());
        return "listar-reunion";
    }

    @RequestMapping(value = "/reunion/" ,method = RequestMethod.POST)
    public ResponseEntity agregarReunion (@RequestBody Reunion reunion){
        return ResponseEntity.ok(reunionService.agregarReunion(reunion));
    }
}















