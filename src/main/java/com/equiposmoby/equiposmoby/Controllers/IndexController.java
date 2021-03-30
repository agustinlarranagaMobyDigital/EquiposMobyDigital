package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Integrante;
import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    IntegranteService integranteService;

    @GetMapping("/app")
    public String index(Model model , HttpSession session){

        if(session.getAttribute("usuario") == null){
            return "redirect:/login/";
        }



        User userlog = (User) session.getAttribute("usuario");
        Integrante integrante = integranteService.getIntegranteByEmail(userlog.getEmail());
        model.addAttribute("integrante" , integrante);
        return "index";
    }
}
