package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.User;
import com.equiposmoby.equiposmoby.Services.IUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/login")
public class SessionController {

    @Autowired
    private IUsuarioServices usuarioServices;
    @GetMapping("/")
    public String loginView(Model model, HttpSession session ){

        if( session.getAttribute("User") != null){
            return "redirect:/app";
        }
        model.addAttribute("titulo" , "login");
        model.addAttribute("mensaje" , "login");

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password , HttpServletRequest request , Model model){
        model.addAttribute("mensaje" , "login");
        model.addAttribute("titulo" , "login");


        Map<String , String> error = new HashMap<>();
        User logUser = usuarioServices.buscar(email , password);

        if (email.isEmpty()){
            error.put("email", "No puede estar vacio");
        }

        if(password.isEmpty()){
            error.put("password" , "No puede estar vacio");
        }

        if(logUser.getEmail().isEmpty()){
            error.put("incorrecto" , "usuario o contraseña no son correctos");
        }

        if(! error.isEmpty()){
            model.addAttribute("email" , email);
            model.addAttribute("password" , password);
            model.addAttribute("error" , error);
            return  "login";
        }

        request.getSession().setAttribute("User", logUser);
        return "redirect:/app";
    }

    @GetMapping("/registration")
    public String registrationView(Model model , HttpSession session){

        if( session.getAttribute("User") != null){
            return "redirect:/app";
        }
        User usuario = new User();;
        model.addAttribute("mensaje" , "registration");
        model.addAttribute("titulo" , "registration");
        model.addAttribute("usuario" , usuario);

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid User usuario , BindingResult result, Model model ){

        model.addAttribute("mensaje" , "llego");
        model.addAttribute("titulo" , "resultados");
        model.addAttribute("usuario" , usuario);
        if(result.hasErrors()){
            Map <String , String> error = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                error.put(err.getField(), "el campo " . concat(err.getField()).concat(" ").concat(Objects.requireNonNull(err.getDefaultMessage())));});

            model.addAttribute("error" , error);

            return  "registration";
        }

        usuarioServices.agregar(usuario);

        return "redirect:/login/";
    }
}
