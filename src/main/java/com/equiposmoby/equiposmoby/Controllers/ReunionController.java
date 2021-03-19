package com.equiposmoby.equiposmoby.Controllers;

import com.equiposmoby.equiposmoby.Models.Daos.ReunionDaoImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReunionController {

    @Autowired
    private ReunionDaoImple reunionDao;

    @RequestMapping( "/reuniones") // Si esta vacio, implicitamente es GET
    public String listar(Model model){
        model.addAttribute("titulo","Listado de las reuniones");
        model.addAttribute("reuniones", reunionDao.traerTodas());
        return "listar-reuniones";
    }
}
