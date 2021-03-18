package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.IUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private IUsuarioServices userService;


    @RequestMapping("/listado")
    public String listar(Model model){
        model.addAttribute("titulo" , "Listado de Usuarios");
        model.addAttribute("usuarios" , userService.traerTodas());
        return "listar-usuarios";
    }

    @RequestMapping("/buscar")
    public String buscar(Model model){
        model.addAttribute("titulo" , "Buscar Isaias");
        model.addAttribute("usuario" , userService.buscar("isaias", "isaias"));
        return "buscar-uno";
    }

    @RequestMapping("/agregar")
    public String agregar(Model model){
        User isaias = new User();
        isaias.setEmail("isaias@isaias.com");
        isaias.setPassword("isaias22");
        userService.agregar(isaias);
        model.addAttribute("titulo" , "Agregar");
        model.addAttribute("usuarios" , userService.traerTodas());
        return "listar-usuarios";
    }


}
