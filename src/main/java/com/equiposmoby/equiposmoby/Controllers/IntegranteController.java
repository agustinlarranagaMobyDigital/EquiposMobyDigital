package com.equiposmoby.equiposmoby.Controllers;


import com.equiposmoby.equiposmoby.Models.Editors.EquipoPropertieEditor;
import com.equiposmoby.equiposmoby.Models.Editors.LenguajePropertieEditor;
import com.equiposmoby.equiposmoby.Models.Editors.PuestoPropertieEditor;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import com.equiposmoby.equiposmoby.Models.Entity.Puesto;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import com.equiposmoby.equiposmoby.Services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IntegranteController {

    private String titulo = "Equipos Moby Digital!";

    @Autowired
    private IntegranteService integranteService;

    @Autowired
    private PuestoPropertieEditor puestoPropertieEditor;
    @Autowired
    private LenguajePropertieEditor lenguajePropertieEditor;

    @Autowired
    private EquipoPropertieEditor equipoPropertieEditor;

    @Autowired
    private SessionService sessionService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Puesto.class,"puesto",puestoPropertieEditor);
        binder.registerCustomEditor(Lenguaje.class,"lenguajes",lenguajePropertieEditor);

       // binder.registerCustomEditor(Equipo.class,"equipo",equipoPropertieEditor);
    }


    @RequestMapping(value = "/formIntegrante")
    public String formIntegrante (Model model , HttpSession session){


        Integrante integrante = new Integrante();

        List<Puesto> listaPuestos= integranteService.getPuestos();

        List<Lenguaje> listaLenguajes = integranteService.getLenguajes();
      //  List<Equipo> listaEquipos = integranteService.getEquipos();

        model.addAttribute("titulo",titulo);
        model.addAttribute("h1","Formulario del nuevo empleado de Moby Digital!");
        model.addAttribute("integrante",integrante);
        model.addAttribute("listaPuestos",listaPuestos);
        model.addAttribute("listaLenguajes",listaLenguajes);
    //    model.addAttribute("listaEquipos",listaEquipos);

        return sessionService.sesionIniciada(session , "formIntegrante") ;
    }


    @PostMapping(value = "/formIntegrante")
    public String addIntegrante(@Valid Integrante integrante,BindingResult result, Model model, 
                                @RequestParam String email,@RequestParam String password , HttpSession session){

        // 1.usuario 2.equipo 3.agenda 4.lenguajes 5.puesto
        Map<String,String> errores = integranteService.crearUsuario(result,email,password);

        if(errores.isEmpty()){

            // agarro el ultimo usuario creado [el de arriba] y lo guardo en Integrante
            User user = integranteService.getUltimoUserByEmail(email);
            integrante.setUsuario(user);

            // si eligio lider, le asigno true al campo booleano

            if(integrante.getPuesto().getNombre().equals("lider")){
                integrante.setJefe(true);
            }

            // guardo en la base de datos
            integranteService.add(integrante);

            // muestro la lista
            return sessionService.sesionIniciada(session , "redirect:/listaIntegrantes") ;
        }else{
            model.addAttribute("errores", errores);
            return sessionService.sesionIniciada(session , "formIntegrante") ;
        }
    }

    @RequestMapping(value ="/formIntegrante/{id}")
    public String editar(Model model, @PathVariable(value = "id") Integer id , HttpSession session){

        // Agarrando el integrante a editar
        Integrante integrante = null;
        if(id > 0 ){
            integrante = integranteService.getById(id);
        }

        // Cargando las listas
        List<Puesto> listaPuestos= integranteService.getPuestos();
        List<Lenguaje> listaLenguajes = integranteService.getLenguajes();
        //  List<Equipo> listaEquipos = integranteService.getEquipos();

        // Cargando el model
        model.addAttribute("titulo",titulo);
        model.addAttribute("h1","Formulario del nuevo empleado de Moby Digital!");
        model.addAttribute("integrante",integrante);
        model.addAttribute("listaPuestos",listaPuestos);
        model.addAttribute("listaLenguajes",listaLenguajes);
        //    model.addAttribute("listaEquipos",listaEquipos);

        return sessionService.sesionIniciada(session , "formIntegrante") ;
    }

    @RequestMapping(value ="/eliminar/{id}")
    public String eliminar(Model model, @PathVariable(value = "id") Integer id , HttpSession session){

        if(session.getAttribute("Usuario") != null){
            integranteService.eliminar(id);
        }
        return sessionService.sesionIniciada(session , "listaIntegrantes") ;
    }


    @RequestMapping(value = "/listaIntegrantes")
    public String listarIntegrantes (Model model , HttpSession session){

        List<Integrante> lista = integranteService.getOrderIntegrante();

        model.addAttribute("titulo",titulo);
        model.addAttribute("h1","Lista de los empleados de Moby Digital!");
        model.addAttribute("lista",lista);

        return sessionService.sesionIniciada(session , "listaIntegrantes") ;
    }
}
