package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Services.CuentaService;
import com.equiposmoby.equiposmoby.Services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping("/cuenta")
    public String listar(Model model, HttpSession session){
        model.addAttribute("titulo","listado de cuentas");
        model.addAttribute("cuentas",cuentaService.traerTodas());
        return sessionService.sesionIniciada(session,"listar-cuentas");
    }

}
