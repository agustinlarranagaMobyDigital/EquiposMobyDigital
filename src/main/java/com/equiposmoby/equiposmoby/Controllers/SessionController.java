package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class SessionController {


    @Autowired
    private SessionService sessionService;

    @GetMapping("/login")
    public String loginView(Model model , HttpSession session){

        User usuario = new User();
        model.addAttribute("titulo" , "Iniciar Sesion");
        model.addAttribute("mensaje" , "Iniciar Sesion");
        model.addAttribute("usuario" , usuario);
        return sessionService.sesionIniciada(session , "login");
    }

    @PostMapping("/login")
    public String login(User usuario,  HttpServletRequest request , Model model){
        model.addAttribute("mensaje" , "Iniciar Sesion");
        model.addAttribute("titulo" , "Iniciar Sesion");
        System.out.println("controller email: " + usuario.getEmail());
        System.out.println("controller password: " +usuario.getPassword());
        Map<String , String> error = sessionService.crearSesion(usuario, request);
        System.out.println(error);
        if(!(error.isEmpty())){
            model.addAttribute("email", usuario.getEmail());
            model.addAttribute("password", usuario.getPassword());
            model.addAttribute("error", error);
            System.out.println("error1");
            return "login";
        }else{
            System.out.println("entro");
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
