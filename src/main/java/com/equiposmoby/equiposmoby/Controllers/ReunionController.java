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

    @Autowired
    private ReunionService reunionService;

    @RequestMapping( "/reuniones") // Si esta vacio, implicitamente es GET
    public String listar(Model model){
        model.addAttribute("titulo","Listado de las reuniones");
        model.addAttribute("reuniones", reunionService.traerTodas());
        return "listar-reuniones";
    }

    @RequestMapping(value = "/reunion/" ,method = RequestMethod.POST)
    public ResponseEntity agregarReunion (@RequestBody Reunion reunion){

        return ResponseEntity.ok(reunionService.agregarReunion(reunion));
    }
}














