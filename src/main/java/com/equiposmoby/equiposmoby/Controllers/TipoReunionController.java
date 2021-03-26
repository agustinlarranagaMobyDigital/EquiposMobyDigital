package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.TipoReunion;
import com.equiposmoby.equiposmoby.Services.TipoReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tipoReunion")
public class TipoReunionController {

    @Autowired
    private TipoReunionService tipoReunionService;

    @GetMapping("/")
    public ResponseEntity<List<TipoReunion>> traerTodas(){

        return ResponseEntity.ok(tipoReunionService.getAll());
    }
}
