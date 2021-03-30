package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Editors.EquipoPropertieEditor;
import com.equiposmoby.equiposmoby.Models.Editors.TipoReunionropertieEditor;
import com.equiposmoby.equiposmoby.Models.Entity.*;
import com.equiposmoby.equiposmoby.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller()
public class ReunionController {

    private String titulo = "Reuniones MobyDigital";

    @Autowired
    private ReunionService reunionService;

    @Autowired
    private TipoReunionService tiposReunionService;

    @Autowired
    private IntegranteService integranteService;

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private ValidacionesService validacionesService;

    @Autowired
    private TipoReunionropertieEditor tipoReunionropertieEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(TipoReunion.class,"tipoReunion",tipoReunionropertieEditor);

    }

    @RequestMapping(value = "/formReunion")
    public String addReunionView(Model model){
        Reunion reunion = new Reunion();
        List<TipoReunion> tiposReunion = tiposReunionService.getAll();
        model.addAttribute("titulo","Agregar Reunion");
        model.addAttribute("reunion",reunion);
        model.addAttribute("tipoReunion" , tiposReunion);
        return "agregar-reunion";
    }

    @PostMapping(value = "/agregarReunion")
    public String addReunion(Model model , Reunion reunion){
        Map<String,String> errores = new HashMap<>();

        if(validacionesService.revisarReunion(reunion,errores)){
            //reunion.setTipoReunion(tiposReunionService.getById(id));
            reunionService.agregar(reunion);
            return "redirect:/listarReuniones";
        }
        else{
            List<TipoReunion> tiposReunion = tiposReunionService.getAll();
            model.addAttribute("titulo","Agregar Reunion");
            model.addAttribute("reunion",reunion);
            model.addAttribute("errores",errores);
            model.addAttribute("tipoReunion" , tiposReunion);
            return "agregar-reunion";
        }
    }

    @RequestMapping( "/listarReuniones") // Si esta vacio, implicitamente es GET
    public String listarReuniones(Model model){
        List<Reunion> reuniones = reunionService.traerTodas();
        model.addAttribute("reuniones",reuniones);
        return "listar-reuniones";
    }

    @RequestMapping( "/listarReuniones/{id}") // Si esta vacio, implicitamente es GET
    public String listarReunionesPorTipo(Model model ,@PathVariable(value = "id") Integer id){
        if(id > 0){
            Integrante integrante = integranteService.getById(id);
            if(integrante != null){
                List<Reunion> reuniones = reunionService.obtenerSegunTipoIntegrante(integrante.getPuesto().getNombre());
                model.addAttribute("integrante",integrante);
                model.addAttribute("reuniones",reuniones);
                return "reuniones-disponibles";
            }
        }
        return "redirect:/app";

    }

    @RequestMapping( "/listarReuniones/{id}/{reunion}")
    public String listarReunionesPorTipo(@PathVariable(value = "id") Integer id, @PathVariable(value = "reunion") Integer reunion){
        Integrante integrante = integranteService.getById(id);
        Reunion reu = reunionService.getById(reunion);
        System.out.println("id:" + integrante.getAgenda().getIdAgenda());
        agendaService.agregar(integrante.getAgenda().getIdAgenda() , reu);
        return "redirect:/listarReuniones/" + id +"";
    }

}















