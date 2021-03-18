package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Daos.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private IUserDao userDao;

    @RequestMapping("/listado")
    public String listar(Model model){
        model.addAttribute("titulo" , "Listado de Usuarios");
        model.addAttribute("usuarios" , userDao.obtenerLista());
        return "listar-usuarios";
    }
}
