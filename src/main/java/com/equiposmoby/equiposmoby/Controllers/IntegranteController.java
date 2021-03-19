package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Editors.LenguajePropertieEditor;
import com.equiposmoby.equiposmoby.Models.Editors.PuestoPropertieEditor;
import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.Lenguaje;
import com.equiposmoby.equiposmoby.Models.Entity.Puesto;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Puesto.class,"puesto",puestoPropertieEditor);
        binder.registerCustomEditor(Lenguaje.class,"lenguajes",lenguajePropertieEditor);
    }


    @RequestMapping(value = "/formIntegrante")
    public String formIntegrante (Model model){

        Integrante integrante = new Integrante();

        List<Puesto> listaPuestos= integranteService.getPuestos();

        List<Lenguaje> listaLenguajes = integranteService.getLenguajes();
        
        model.addAttribute("titulo",titulo);
        model.addAttribute("h1","Formulario del nuevo empleado de Moby Digital!");
        model.addAttribute("integrante",integrante);
        model.addAttribute("listaPuestos",listaPuestos);
        model.addAttribute("listaLenguajes",listaLenguajes);

        return "formIntegrante";
    }


    @PostMapping(value = "/addIntegrante")
    public String addIntegrante(@Valid Integrante integrante,BindingResult result, Model model, 
                                @RequestParam String email,@RequestParam String password){


        
        // INCORPORAR A LA CLASE INTEGRANTE
        // 1.usuario 2.equipo 3.agenda 4.lenguajes 5.puesto
        Map<String,String> errores = integranteService.crearUsuario(result,email,password);
        System.out.println("errores = " + errores);
        if(errores.isEmpty()){

            //1. usuario -->  bien
            User user = integranteService.getUltimoUserByEmail(email);
            integrante.setUsuario(user);
            // 5. puesto --> bien
            if(integrante.getPuesto().getNombre().equals("lider")){
                integrante.setJefe(true);
            }

            //PRUEBAS !!!!
            System.out.println("integrante" + integrante.toString());

            /*List<Lenguaje> listaLenguajes = integranteService.getLenguajes();
            integrante.setLenguajes(listaLenguajes);

            integranteService.add(integrante);
*/
            return  "redirect:/formIntegrante";
        }else{
            model.addAttribute("errores", errores);
            return "formIntegrante";
        }


    }

}
