package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Entity.Cuenta;
import com.equiposmoby.equiposmoby.Models.Entity.Reunion;
import com.equiposmoby.equiposmoby.Services.CuentaService;
import com.equiposmoby.equiposmoby.Services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private SessionService sessionService;


    @RequestMapping("/agregarCuenta")
    public String agregarCuenta(Model model){
        model.addAttribute("titulo" , "Agregar Cuenta");
        model.addAttribute("cuenta" , new Cuenta());
        return "agregar-cuenta";
    }

    @PostMapping("/agregarCuenta")
    public String guardarCuenta(Cuenta cuenta , Model model){
        model.addAttribute("titulo" , "Agregar Cuenta");
        model.addAttribute("cuenta" , cuenta);
        cuentaService.agregar(cuenta);
        return "redirect:/listarCuentas";
    }

    @RequestMapping("/listarCuentas")
    public String listar(Model model, HttpSession session){
        model.addAttribute("titulo","listado de cuentas");
        model.addAttribute("cuentas",cuentaService.traerTodas());
        return sessionService.sesionIniciada(session,"listar-cuentas");
    }

}
