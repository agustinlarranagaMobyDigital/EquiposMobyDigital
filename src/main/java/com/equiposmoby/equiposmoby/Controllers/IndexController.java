package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping("/app")
    public String index(Model model , HttpSession session){

        if(session.getAttribute("usuario") == null){
            return "redirect:/login/";
        }

        User userlog = (User) session.getAttribute("usuario");
        model.addAttribute("usuario" , userlog);
        return "index";
    }
}
