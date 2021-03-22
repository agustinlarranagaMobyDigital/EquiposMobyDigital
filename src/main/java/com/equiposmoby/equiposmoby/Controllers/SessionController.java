package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.IUsuarioServices;
import com.equiposmoby.equiposmoby.Services.IntegranteService;
import com.equiposmoby.equiposmoby.Services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Map;

@Controller
public class SessionController {


    @Autowired
    private SessionService sessionService;

    @GetMapping("/login")
    public String loginView(Model model , HttpSession session){

        model.addAttribute("titulo" , "Iniciar Sesion");
        model.addAttribute("mensaje" , "Iniciar Sesion");
        return sessionService.sesionIniciada(session , "login");
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password , HttpServletRequest request , Model model) throws ParseException {
        model.addAttribute("mensaje" , "Iniciar Sesion");
        model.addAttribute("titulo" , "Iniciar Sesion");

        Map<String , String> error = sessionService.crearSesion(email , password , request);

        if(!error.isEmpty()) {
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("error", error);
            return "login";
        }else{
            return "redirect:/app";
        }


    }

    @GetMapping("/registration")
    public String registrationView(Model model , HttpSession session){

        User usuario = new User();
        model.addAttribute("mensaje" , "Registrarse");
        model.addAttribute("titulo" , "Registrarse");
        model.addAttribute("usuario" , usuario);

        return sessionService.sesionIniciada(session , "registration");
    }

    @PostMapping("/registration")
    public String registration(@Valid User usuario , BindingResult result, Model model ){

        model.addAttribute("mensaje" , "Registrarse");
        model.addAttribute("titulo" , "Registrarse");
        model.addAttribute("usuario" , usuario);
        Map<String , String> error = sessionService.crearUsuario(result , usuario);


        if(!error.isEmpty()){
            model.addAttribute("error" , error );
            return  "registration";
        }



        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String salir(HttpSession session){
        session.invalidate();
        return "redirect:/login";

    }


}
